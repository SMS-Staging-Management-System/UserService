# UserService
repository for user service

# Database Setup
run user-service.sql file to create database schema. Will create the schema itself. Does not need schema setup.

# Environment Variables


url for interracting with cognito endpoints  
```COGNITO_URL: ask blake```  

key required for certain cognito endpoints  
```COGNITO_KEY: ask blake```

Password to access the database  
```DB_PASSWORD: password```

Schema for the database  
```DB_SCHEMA: user_service```

Url pointing to the database that the service will use  
```DB_URL: jdbc:postgresql://localhost:5432/postgres```

Username to log into the database   
```DB_USERNAME: username```

The default gateway can be changed in the application.yml file.
Please set this stage to prod for production or dev for development mode. Setting to dev will void auth requirements.  
```DEPLOYMENT_STAGE: dev```

### The below environment variables are only needed for production
Gateway URL  
```GATEWAY_URL: ask blake if needed```


