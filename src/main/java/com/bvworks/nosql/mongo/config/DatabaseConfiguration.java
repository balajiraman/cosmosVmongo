package com.bvworks.nosql.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.bvworks.nosql.mongo.repository")

@Configuration
public class DatabaseConfiguration  extends AbstractMongoClientConfiguration{

    @Value("${mongodbUri}")
    private String dbUri;

    @Value("${databaseName}")
    private String database;


    @Override
    protected void configureClientSettings (MongoClientSettings.Builder builder){
        ConnectionString connectionString = new ConnectionString(dbUri);
        builder.applyConnectionString(connectionString);
    }

    @Override
    protected String getDatabaseName(){
        return database;
    }
}
