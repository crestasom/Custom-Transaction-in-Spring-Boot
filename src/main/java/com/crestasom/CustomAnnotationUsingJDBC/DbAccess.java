package com.crestasom.CustomAnnotationUsingJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class DbAccess {
	private static final Logger LOG = LoggerFactory.getLogger(DbAccess.class);

	MyConnection conn;
	@Autowired
	WebApplicationContext context;

	public void initCon() {
		conn = context.getBean(MyConnection.class);
		conn.openConnection();
	}

	public void closeCon() {
		conn.closeConnection();
	}

	/**
	 * method for starting transaction in manual transaction management invoked from
	 * AOP class before any database update is done
	 */
	public void beginTransaction() {
		try {
			conn.getCon().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.info("Cannot begin transaction: ");
		}
	}

	/**
	 * method for commit transaction in manual transaction management invoked from
	 * AOP class after all database update is done
	 */
	public void commitTransaction() {

		try {
			conn.getCon().commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.info("Cannot commit transaction: " + e.getCause().toString());
		}
	}

	/**
	 * method for rollback transaction in manual transaction management invoked from
	 * AOP class if there is any exception while database update is done
	 */
	public void rollBackTransaction() {

		try {
			conn.getCon().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.info("Cannot rollback transaction: " + e.getCause().toString());
		}
	}

	/**
	 * queries and returns the result list for the sql uses prepared statement to
	 * execute the queries
	 * 
	 * @param sql sql query with dynamic parameter binding
	 * @return ResultSet containing the result set
	 * @return empty list if there is no matching tuple in database for given query
	 */

	public synchronized List<Student> getResultList(String sql) {

		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.getCon().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info(e.getMessage());
		}

		return students;

	}

	/**
	 * used to update with manual transcation management should be used inside the
	 * method annotated with @CustomTransactional, transcation is managed using
	 * Spring AOP inside AOP class
	 * 
	 * @param sql  sql query with dynamic parameter binding
	 * @param args arguments to be bound in runtime in the sql query
	 */
	public void executeUpdate(String sql, List<String> args) {
		try {
			PreparedStatement stmt = conn.getCon().prepareStatement(sql);
			for (int i = 0; i < args.size(); i++) {

				stmt.setString(i + 1, args.get(i));
			}
			stmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.info("Throwing exception from dbaccess" + ex.getMessage());
			throw new RuntimeException("SQLException:" + ex.getMessage());
		}

	}
}
