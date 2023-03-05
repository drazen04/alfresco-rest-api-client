# alfresco-rest-api-client

## Alfresco Api
To use the alfresco api (in particular, before tests) in the local environment run the following from a bash shell (if you use Window, you can use git-bash for convenience):
```bash
make run-api
```

The command will pull up services via docker-compose. The docker compose was created with the [alfresco docker generator](https://github.com/Alfresco/alfresco-docker-installer).
