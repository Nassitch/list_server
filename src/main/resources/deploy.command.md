## BASE_URL :

http://82.112.241.18:8080

*ssh log*
``ssh root@82.112.241.18``

### Processus :

* Compilation.

```
mvn clean package -DskipTests
```

* Exécution.

```
java -jar target/le_fichier.jar
```

### Stream :

* Lancer.

```
nohup java -jar le_fichier.jar &
```

* Voir

```
ps -ef | grep java
```

* Stopper.

```
kill 16293
```