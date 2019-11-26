package com.yerti.runecraft.storage;


import com.yerti.core.player.RunePlayer;
import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.managers.PlayerSkillManager;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.Collection;
import java.util.UUID;

public class MySQLSkillStorage implements StorageManager {


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


    /**
     * Retrieves the MySQL information from the config for later use
     *
     * Also creates the XP and Level tables if required
     */
    public void setup() {

        if (!plugin.getConfig().getBoolean("mysql-enabled")) {
            return;
        }


        try {
            StringBuilder levelStatement = new StringBuilder();
            StringBuilder xpStatement = new StringBuilder();


            for (Enum skill : SkillType.values()) {
                levelStatement.append(skill.toString().toLowerCase()).append(" decimal");
                levelStatement.append(skill == SkillType.values()[SkillType.values().length - 1] ? "" : ", ");

            }

            //Add all of the skills for their xp
            for (Enum skill : SkillType.values()) {
                xpStatement.append(skill.toString().toLowerCase()).append(" decimal");
                xpStatement.append(skill == SkillType.values()[SkillType.values().length - 1] ? "" : ", ");
            }

            //Create levels and experience
            prepareStatement("CREATE TABLE IF NOT EXISTS RuneCraftSkillLevels (user_id numeric, PRIMARY KEY(user_id), " + levelStatement + ")").execute();
            prepareStatement("CREATE TABLE IF NOT EXISTS RuneCraftSkillXP (user_id numeric, PRIMARY KEY(user_id), "    + xpStatement + ")").execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes the player from the MySQL database
     * @param player
     */
    @Override
    public void removePlayer(Player player) {
        /*try {

            PreparedStatement levelStatement = prepareStatement("DELETE FROM RuneCraftSkillLevels WHERE user_id LIKE ?");
            PreparedStatement xpStatement = prepareStatement("DELETE FROM RuneCraftSkillXP WHERE user_id LIKE ?");

            levelStatement.setString(1, manager.getPlayer().getUniqueId().toString());
            levelStatement.execute();

            xpStatement.setString(1, manager.getPlayer().getUniqueId().toString());
            xpStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
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
     * @param player Player parameter
     */
    @Override
    public void savePlayer(Player player)  {

        RunePlayer player1 = RunePlayer.getPlayer(player);
        PlayerSkillManager manager = player1.getLevelManager();

        Collection<Integer> levels = manager.getLevels().values();
        Collection<Double> experience = manager.getLevelsXp().values();


        PreparedStatement statement = null;
        try {




            //TODO: Make this neater

            //Saving levels
            StringBuilder levelStatement = new StringBuilder();
            levelStatement.append("UPDATE RuneCraftSkillLevels SET ");

            for (SkillType skill : manager.getLevels().keySet()) {
                levelStatement.append(skill.toString().toLowerCase()).append(" = ?, ");
            }

            levelStatement.append("WHERE user_id = ?");

            statement = prepareStatement(levelStatement.toString());

            for (int i = 1; i <= levels.size(); i++) {
                statement.setInt(i, levels.toArray(new Integer[0])[i]);
            }

            statement.setString(levels.size() + 1, player.getUniqueId().toString());

            statement.close();

            //Saving experience
            StringBuilder experienceStatement = new StringBuilder("UPDATE RuneCraftSkillXP SET ");

            for (SkillType skill : manager.getLevels().keySet()) {
                experienceStatement.append(skill.toString().toLowerCase()).append(" = ?, ");
            }

            experienceStatement.append("WHERE user_id = ?");

            statement = prepareStatement(experienceStatement.toString());

            for (int i = 1; i <= experience.size(); i++) {
                statement.setDouble(i, experience.toArray(new Double[0])[i]);
            }

            statement.setString(experience.size() + 1, player.getUniqueId().toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private Connection con = null;

    public MySQLSkillStorage(Plugin plugin) {

        this.plugin = plugin;

        FileConfiguration configuration = plugin.getConfig();

        //Fetch MySQL information from the config
        hostName = configuration.getString("mysql.host");
        port = String.valueOf(configuration.getInt("mysql.port"));
        databaseName = configuration.getString("mysql.database");
        user = configuration.getString("mysql.username");
        password = configuration.getString("mysql.password");




        final String driver = "com.mysql.jdbc.Driver";
        String connection = "jdbc:mysql://" + hostName + ":" + port + "/" + databaseName;


        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connection, user, password);


        } catch (SQLException e) {
            try {
                connection = "jdbc:mysql://" + hostName + ":" + port;
                con = DriverManager.getConnection(connection, user, password);

                execute("CREATE DATABASE IF NOT EXISTS " + databaseName);

                connection = "jdbc:mysql://" + hostName + ":" + port + "/" + databaseName;
                con = DriverManager.getConnection(connection, user, password);

                setup();

            } catch (SQLException e1) {
                throwSQLException(e1, "Failed to create database during initialisation. Most likely due to incorrect database settings in config file.");
            }

            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("[RuneSQL] " + "SQL Drivers not installed. The server and java installation must support JDBC connections.");
            if (RuneCraft.getInstance().debugMode()) {
                e.printStackTrace();
            }
        }


    }

    public void throwSQLException(SQLException e, String msg) {
        if (msg != null) {
            System.out.println("[RuneSQL] " + msg);
        }
        if (RuneCraft.getInstance().debugMode()) {
            e.printStackTrace();
        }
    }

    public ResultSet query(PreparedStatement stmt) {
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();
        } catch (SQLException e4) {
            throwSQLException(e4, "General Database error. Enable debug-mode for more info.");
        }

        return rs;
    }

    public ResultSet query(String string) {
        ResultSet rs = null;

        try {
            PreparedStatement stmt = prepareStatement(string);
            rs = stmt.executeQuery();
        } catch (SQLException e4) {
            throwSQLException(e4, "General Database error. Enable debug-mode for more info.");
        }

        return rs;
    }

    public void execute(PreparedStatement stmt) {

        try {
            stmt.execute();
        } catch (SQLException e4) {
            throwSQLException(e4, "General Database error. Enable debug-mode for more info.");
        }
    }

    public void execute(String s) throws SQLException {

        PreparedStatement stmt = prepareStatement(s);
        stmt.execute();
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            throwSQLException(e, "General Database error. Could not close SQL connection after use! Enable debug-mode for more info.");
        }
    }

    public PreparedStatement prepareStatement(String s) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(s);
        } catch (SQLException e) {
            throwSQLException(e, "General Database error. Could not convert " + s + " to a prepared database statement! Enable debug-mode for more info.");
        }

        return stmt;
    }




}
