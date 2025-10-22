package org.example.mindplomv2.dao;

import org.example.mindplomv2.model.Course;
import org.example.mindplomv2.utils.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseDAO {
    private static final Logger logger = Logger.getLogger(CourseDAO.class.getName());

    // SQL Queries
    private static final String INSERT_COURSE_SQL = "INSERT INTO courses (Nom_du_cours) VALUES (?)";
    private static final String SELECT_ALL_COURSES_SQL = "SELECT * FROM courses";
    private static final String DELETE_COURSE_SQL = "DELETE FROM courses WHERE id = ?";
    private static final String UPDATE_COURSE_SQL = "UPDATE courses SET Nom_du_cours = ? WHERE id = ?";

    // Add a new course
    public boolean addCourse(Course course) {
        boolean rowInserted = false;
        String errorMessage = "Unknown error adding course";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_COURSE_SQL)) {

            if (course == null || course.getName() == null || course.getName().isEmpty()) {
                throw new IllegalArgumentException("Course name cannot be null or empty");
            }

            statement.setString(1, course.getName());  // Set the course name
            int rowsAffected = statement.executeUpdate();  // Execute the insert

            rowInserted = rowsAffected > 0;

        } catch (SQLException e) {
            errorMessage = "SQL error adding course: " + e.getMessage();
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            errorMessage = "Validation error: " + e.getMessage();
        } finally {
            if (!rowInserted) {
                System.err.println(errorMessage);
            }
        }
        return rowInserted;
    }

    // Get all courses
    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_COURSES_SQL)) {

            while (resultSet.next()) {

                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName( resultSet.getString("Nom_du_cours"));
                courses.add(course);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all coursse" , e);

        }

        return courses;
    }

    // Delete a course
    public boolean deleteCourse(int id) {
        boolean rowDeleted = false;
        String errorMessage = "Unknown error deleting course";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_SQL)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            rowDeleted = rowsAffected > 0;

        } catch (SQLException e) {
            errorMessage = "SQL error deleting course: " + e.getMessage();
            e.printStackTrace();
        } finally {
            if (!rowDeleted) {
                System.err.println(errorMessage);
            }
        }
        return rowDeleted;
    }

    // Update a course
    public boolean updateCourse(Course course) {
        boolean rowUpdated = false;
        String errorMessage = "Unknown error updating course";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE_SQL)) {

            if (course == null || course.getName() == null || course.getName().isEmpty()) {
                throw new IllegalArgumentException("Course name cannot be null or empty");
            }

            statement.setString(1, course.getName());
            statement.setInt(2, course.getId());
            int rowsAffected = statement.executeUpdate();

            rowUpdated = rowsAffected > 0;

        } catch (SQLException e) {
            errorMessage = "SQL error updating course: " + e.getMessage();
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            errorMessage = "Validation error: " + e.getMessage();
        } finally {
            if (!rowUpdated) {
                System.err.println(errorMessage);
            }
        }
        return rowUpdated;
    }
}
