# b-Rubix Identity

## Prerequisite  ##
    https://stackoverflow.com/questions/38165131/spring-security-oauth2-accept-json/38284206#38284206
    
    generate token  
    curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'username=user1&password=password1&grant_type=password&client_id=brubix&client_secret=secret' "http://localhost:8090/v1/oauth/token"
    
    validate token
    http://localhost:8090/v1/oauth/check_token?token=84cb7041-dff0-44f8-8692-8b70e1873072

    refresh token
    http://localhost:8090/v1/oauth/token?grant_type=refresh_token&refresh_token=094b7d23-973f-4cc1-83ad-8ffd43de1845
