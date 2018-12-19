# UserService
repository for user service

#Environment Variables

Url for cognito jwt verification, the url should contain /dev or /prod so it can point to the appropriate aws cognito  
the application will append /cognito/register to register user or /cognito/auth to verify jwt token

```COGNITO_URL: https://t4o3pxu8dj.execute-api.us-west-2.amazonaws.com/dev/ ```

Password to access the database

```DB_PASSWORD: password```

Schema for the database 

```DB_SCHEMA: sms```

Url pointing to the database that the service will use

```DB_URL: jdbc:postgresql://znc.anorexicseal.com:5432/userservice```

Username to log into the database 

```DB_USERNAME: username```

Gateway URL

```GATEWAY_URL: URL belonging to a deployed serverless that will route all traffic only needed for production```

The default gateway can be changed in the application.yml file.
Please set this stage to prod for production or dev for development mode

```DEPLOYMENT_STAGE: dev```
