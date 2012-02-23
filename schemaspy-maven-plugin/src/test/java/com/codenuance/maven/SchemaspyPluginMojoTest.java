package com.codenuance.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

public class SchemaspyPluginMojoTest {

	@Test
	public void test() throws MojoExecutionException {
		SchemaspyPluginMojo schemaspyPluginMojo = new SchemaspyPluginMojo();
		schemaspyPluginMojo.setPathToDrivers("mysql-connector-java-5.1.9.jar");
		schemaspyPluginMojo.setDatabaseType("mysql");
		schemaspyPluginMojo.setUser("root");
		schemaspyPluginMojo.setDbName("fotozap_staging");
		schemaspyPluginMojo.setHost("localhost");
		schemaspyPluginMojo.execute();
	}

}
