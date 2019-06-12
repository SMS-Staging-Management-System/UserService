# UserService
repository for user service

#Database Variables
run user-service.sql file to create database schema. Will create the schema itself. Does not need schema setup.

#Environment Variables

Url for cognito jwt verification, the url should contain /dev or /prod so it can point to the appropriate aws cognito  
the application will append /cognito/register to register user or /cognito/auth to verify jwt token

```COGNITO_URL: https://t4o3pxu8dj.execute-api.us-west-2.amazonaws.com/dev/ ```
```COGNITO_KEY: ask blake```

Password to access the database

```DB_PASSWORD: password```

Schema for the database 

```DB_SCHEMA: user_service```

Url pointing to the database that the service will use

```DB_URL: jdbc:postgresql://yourDBurl```

Username to log into the database 

```DB_USERNAME: username```

Gateway URL

```GATEWAY_URL: localhost.8765```

The default gateway can be changed in the application.yml file.
Please set this stage to prod for production or dev for development mode

```DEPLOYMENT_STAGE: dev```
