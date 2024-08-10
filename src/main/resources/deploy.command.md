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

* Voir processus sur port 8080tai

```
sudo lsof -i :8080
```

* Voir

```
ps -ef | grep java
```

* Logs

```
tail -f nohup.out
```

* Stopper.

```
kill 16293
```