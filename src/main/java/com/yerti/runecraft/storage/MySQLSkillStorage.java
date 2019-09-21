package com.yerti.runecraft.storage;


import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.managers.LevelManager;
import com.yerti.runecraft.managers.PlayerSkillManager;
import com.yerti.runecraft.managers.Skills;
import com.yerti.runecraft.skills.Skill;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import pro.husk.Database;
import pro.husk.mysql.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.UUID;

public class MySQLSkillStorage implements StorageManager {

    private static MySQL database;

    private Plugin plugin;

    /**
     * Host name for MySQL
     */
    private String hostName;

    /**
     * Port for MySQL
     */
    private String port;

    /**
     * Name for the database
     */
    private String databaseName;

    /**
     * Username for MySQL
     */
    private String user;

    /**
     * Password for MYSQL
     */
    private String password;


    public MySQLSkillStorage(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Retrieves the MySQL information from the config for later use
     *
     * Also creates the XP and Level tables if required
     */
    public void setup() {

        if (!plugin.getConfig().getBoolean("mysql-enabled")) {
            return;
        }

        FileConfiguration configuration = plugin.getConfig();

        //Fetch MySQL information from the config
        hostName = configuration.getString("mysql.host");
        port = String.valueOf(configuration.getInt("mysql.port"));
        databaseName = configuration.getString("mysql.database");
        user = configuration.getString("mysql.username");
        password = configuration.getString("mysql.password");

        database = new MySQL(hostName, port, databaseName, user, password, true, true);
        Connection connection;
        try {
            StringBuilder levelStatement = new StringBuilder();
            StringBuilder xpStatement = new StringBuilder();


            connection = database.openConnection();



            //Add all of the skills for their levels
            for (Enum skill : Skills.values()) {
                levelStatement.append(skill.toString().toLowerCase() + " decimal(64, 0), ");
            }

            //Add all of the skills for their xp
            for (Enum skill : Skills.values()) {
                xpStatement.append(skill.toString().toLowerCase() + " decimal(64, 2), ");
            }

            //Create levels and experience
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS RuneCraftSkillLevels (user_id tinytext, PRIMARY KEY(user_id)" + levelStatement + ")").execute();
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS RuneCraftSkillXP (user_id tinytext, PRIMARY KEY(user_id))"    + xpStatement + ")").execute();

            connection.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes the player from the MySQL database
     * @param manager
     */
    @Override
    public void removePlayer(PlayerSkillManager manager) {
        try {
            Connection connection = database.openConnection();;

            PreparedStatement levelStatement = connection.prepareStatement("DELETE FROM RuneCraftSkillLevels WHERE user_id LIKE ?");
            PreparedStatement xpStatement = connection.prepareStatement("DELETE FROM RuneCraftSkillXP WHERE user_id LIKE ?");

            levelStatement.setString(1, manager.getPlayer().getUniqueID().toString());
            levelStatement.execute();

            xpStatement.setString(1, manager.getPlayer().getUniqueID().toString());
            xpStatement.execute();

            connection.close();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO: Add retrieval of skills
    @Override
    public PlayerSkillManager retrieveManager(UUID id) {



        return null;
    }

    /**
     * Creates a player based off of a PlayerSkillManager
     * @param manager
     */
    @Override
    public void createPlayer(PlayerSkillManager manager) {

    }

    /**
     * Stores the skills from a manager into a MySQL database
     * @param manager Player Skill Manager
     */
    @Override
    public void savePlayer(PlayerSkillManager manager)  {

        Collection<Integer> levels = manager.getLevels().values();
        Collection<Double> experience = manager.getLevelsXp().values();


        //Initialize database
        MySQL database = new MySQL(hostName, port, databaseName, user, password, true, true);
        Connection connection;
        PreparedStatement statement = null;
        try {

            connection = database.openConnection();


            //TODO: Make this neater

            //Saving levels
            StringBuilder levelStatement = new StringBuilder();
            levelStatement.append("UPDATE RuneCraftSkillLevels SET ");

            for (Skill skill : manager.getLevels().keySet()) {
                levelStatement.append(skill.getName().toLowerCase() + " = ?, ");
            }

            levelStatement.append("WHERE user_id = ?");

            statement = connection.prepareStatement(levelStatement.toString());

            for (int i = 1; i <= levels.size(); i++) {
                statement.setInt(i, levels.toArray(new Integer[levels.size()])[i]);
            }

            statement.setString(levels.size() + 1, manager.getPlayer().getUniqueID().toString());

            statement.close();

            //Saving experience
            StringBuilder experienceStatement = new StringBuilder("UPDATE RuneCraftSkillXP SET ");

            for (Skill skill : manager.getLevels().keySet()) {
                experienceStatement.append(skill.getName().toLowerCase() + " = ?, ");
            }

            experienceStatement.append("WHERE user_id = ?");

            statement = connection.prepareStatement(experienceStatement.toString());

            for (int i = 1; i <= experience.size(); i++) {
                statement.setDouble(i, experience.toArray(new Double[experience.size()])[i]);
            }

            statement.setString(experience.size() + 1, manager.getPlayer().getUniqueID().toString());

            statement.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }




}
