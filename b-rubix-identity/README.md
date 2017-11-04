# b-Rubix Identity

## Prerequisite  ##
    
    Generate access token  
    $ curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'username=123456789&password=admin&grant_type=password&client_id=brubix&client_secret=secret' "http://localhost:8090/v1/oauth/token"

  
    Get user details by access token
    $ curl -i -H "Accept: application/json" -H "Authorization: Bearer 8a9d0c7f-6c1c-431a-a2e0-ce4310f8cc91" -X GET http://localhost:8090/v1/me

  
    Check token endpoint
    http://localhost:8090/v1/oauth/check_token?token=84cb7041-dff0-44f8-8692-8b70e1873072

    Refresh token
    http://localhost:8090/v1/oauth/token?grant_type=refresh_token&refresh_token=094b7d23-973f-4cc1-83ad-8ffd43de1845