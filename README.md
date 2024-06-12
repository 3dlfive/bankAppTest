#### How to run :
after packaging
```
maven package

java -jar target/bankapp-0.0.1-SNAPSHOT.jar
```

After you can go to webpage and see list table of Customers (Tymeleaf):
http://localhost:9000/tl/all

**Note:** you need add some customers and accounts to see them


##### Link for H2:
http://localhost:9000/h2-console/
##### Swagger:
http://localhost:9000/swagger-ui/index.html

You can find list with **example** of used endpoint in folder:
**/scripts/**



### React app
http://localhost:9000

### local react dev: 
```
cd /src/main/frontend
npm run dev`
```

### DB in docker
in folder sripts docker-compose file to run pg in docker
