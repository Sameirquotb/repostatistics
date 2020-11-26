# Repository Statistics
REST microservices that provide statistics about public repos on GitHub.

# Language, Frameworks  and libraries
- Java 8
- Spring Boot
- Spring Web
- Lombok

# End Points

Currently the project proved only one end point to fetch languages statistics

- **language Statistics** : list the languages used by the 100 trending public repos on GitHub.

End Point URL:
```
/repos/languagestatistics
```
Sample Output:

```
[
  {
    "name": "Java",
    "repos": [
      "CobaltStrike",
      "shiro_attack"
    ],
    "count": 2
  },
  {
    "name": "C++",
    "repos": [
      "redpanda",
      "openedr",
      "telegram-bot-api",
      "kphp",
      "hackergame2020-writeups",
      "lab2d"
    ],
    "count": 6
  }
 ]
```

## How to run

- Download source code.
- Open terminal window
- Navigate to source code folder 
- Run (Maven should be installed)
```
mvn clean package
```
- Run 
```
java -jar target/repostatistics-0.0.1-SNAPSHOT.jar
```
- Open Endpoint URL using browser or any HTTP client
```
http://localhost:8080/repos/languagestatistics
```



## Future Enhancement :

- Develop unit test.
- Using [WebClient](https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-webclient.html) instead of RestTemplate to consumer GitHub webservices, WebClient is a modern, alternative HTTP client to RestTemplate. Not only does it provide a traditional synchronous API, but it also supports an efficient nonblocking and asynchronous approach.

