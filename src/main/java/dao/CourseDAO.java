package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Course;
import utils.DatabaseConnection;

public class CourseDAO {

    // Add a new course
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO Course (CourseID, Title, CourseType, Token, Capacity, StartDate, EndDate, FacultyID, TextbookID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getCourseID());
            pstmt.setString(2, course.getTitle());
            pstmt.setString(3, course.getCourseType());
            pstmt.setString(4, course.getToken());
            if (course.getCapacity() != null) {
                pstmt.setInt(5, course.getCapacity());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setDate(6, new java.sql.Date(course.getStartDate().getTime()));
            pstmt.setDate(7, new java.sql.Date(course.getEndDate().getTime()));
            pstmt.setString(8, course.getFacultyID());
            if (course.getTextbookID() != null) {
                pstmt.setInt(9, course.getTextbookID());
            } else {
                pstmt.setNull(9, Types.INTEGER);
            }

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve course by ID
    public Course getCourseByID(String courseID) {
        String sql = "SELECT * FROM Course WHERE CourseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourse(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM Course";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                courses.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Retrieve courses by FacultyID
    public List<Course> getCoursesByFacultyID(String facultyID) {
        String sql = "SELECT * FROM Course WHERE FacultyID = ?";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, facultyID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Update course
    public boolean updateCourse(Course course) {
        String sql = "UPDATE Course SET Title = ?, CourseType = ?, Token = ?, Capacity = ?, StartDate = ?, EndDate = ?, FacultyID = ?, TextbookID = ? WHERE CourseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getCourseType());
            pstmt.setString(3, course.getToken());
            if (course.getCapacity() != null) {
                pstmt.setInt(4, course.getCapacity());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setDate(5, new java.sql.Date(course.getStartDate().getTime()));
            pstmt.setDate(6, new java.sql.Date(course.getEndDate().getTime()));
            pstmt.setString(7, course.getFacultyID());
            if (course.getTextbookID() != null) {
                pstmt.setInt(8, course.getTextbookID());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }
            pstmt.setString(9, course.getCourseID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete course
    public boolean deleteCourse(String courseID) {
        String sql = "DELETE FROM Course WHERE CourseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract Course from ResultSet
    private Course extractCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseID(rs.getString("CourseID"));
        course.setTitle(rs.getString("Title"));
        course.setCourseType(rs.getString("CourseType"));
        course.setToken(rs.getString("Token"));
        int capacity = rs.getInt("Capacity");
        if (!rs.wasNull()) {
            course.setCapacity(capacity);
        } else {
            course.setCapacity(null);
        }
        course.setStartDate(rs.getDate("StartDate"));
        course.setEndDate(rs.getDate("EndDate"));
        course.setFacultyID(rs.getString("FacultyID"));
        int textbookID = rs.getInt("TextbookID");
        if (!rs.wasNull()) {
            course.setTextbookID(textbookID);
        } else {
            course.setTextbookID(null);
        }
        return course;
    }
}
