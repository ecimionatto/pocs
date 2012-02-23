package com.codenuance.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * generate schemaspy site.
 * 
 * @goal generate
 */
public class SchemaspyPluginMojo extends AbstractMojo {

	/**
	 * Type of database (e.g. ora, db2, etc.). Use -dbhelp for a list of
	 * built-in types. Defaults to ora.
	 * 
	 * @parameter expression="${databaseType}"
	 */
	private String databaseType;

	/**
	 * Name of database to connect to
	 * 
	 * @parameter expression="${dbName}" default-value=""
	 */
	private String dbName;

	/**
	 * Valid database user id with read access. A user id is required unless
	 * -sso is specified.
	 * 
	 * @parameter expression="${user}" default-value=""
	 */
	private String user;

	/**
	 * Database schema. This is optional if it's the same as user or isn't
	 * supported by your database. Use -noschema if your database thinks it
	 * supports schemas but doesn't (e.g. older versions of Informix).
	 * 
	 * @parameter expression="${schema}" default-value=""
	 */
	private String schema;

	/**
	 * Password associated with that user. Defaults to no password.
	 * 
	 * @parameter expression="${password}" default-value=""
	 */
	private String password;

	/**
	 * Directory to write the generated HTML/graphs to
	 * 
	 * @parameter expression="${outputDirectory}" default-value=""
	 */
	private String outputDirectory;

	/**
	 * Looks for drivers here before looking in driverPath in
	 * [databaseType].properties. The drivers are usually contained in .jar or
	 * .zip files and are typically provided by your database vendor.
	 * 
	 * @parameter expression="${pathToDrivers}" default-value=""
	 */
	private String pathToDrivers;

	/**
	 * 
	 * @parameter expression="${host}" default-value=""
	 */
	private String host;

	public void execute() throws MojoExecutionException {

		getLog().info("generating database...");

		StringBuilder stringBuilder = new StringBuilder(
				"java -jar schemaspy-5.0.0.jar -o target/site/schemaspy ");
		if (databaseType != null) {
			stringBuilder.append(" -t ");
			stringBuilder.append(databaseType);
		}
		if (dbName != null) {
			stringBuilder.append(" -db ");
			stringBuilder.append(dbName);
		}
		if (user != null) {
			stringBuilder.append(" -u ");
			stringBuilder.append(user);
		}
		if (schema != null) {
			stringBuilder.append(" -s ");
			stringBuilder.append(schema);
		}
		if (password != null) {
			stringBuilder.append(" -p ");
			stringBuilder.append(password);
		}
		if (outputDirectory != null) {
			stringBuilder.append(" -o ");
			stringBuilder.append(outputDirectory);
		}
		if (pathToDrivers != null) {
			stringBuilder.append(" -dp ");
			stringBuilder.append(pathToDrivers);
		}
		if (host != null) {
			stringBuilder.append(" -host ");
			stringBuilder.append(host);
		}

		Runtime rt = Runtime.getRuntime();
		Process pr;
		try {
			getLog().info(stringBuilder.toString());
			pr = rt.exec(stringBuilder.toString());
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		}

		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getErrorStream()));

		String line = null;

		try {
			while ((line = input.readLine()) != null) {
				getLog().info(line);
			}
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		}

	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPathToDrivers(String pathToDrivers) {
		this.pathToDrivers = pathToDrivers;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
