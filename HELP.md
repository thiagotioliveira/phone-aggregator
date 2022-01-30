![Alt text](docs/talkdesk-logo-2021-purple-rgb.png?raw=true "Talkdesk - The Challenge Phase - Nov/2021 - By Thiago Oliveira - thiagoti.com")

Talkdesk - The Challenge Phase - Nov/2021 - By Thiago Oliveira - thiagoti.com

# Read Me First

The goal of this exercise is to build a phone information aggregator API. This system takes a list of phone numbers obtained from user input and returns the count of valid phones broken down per prefix and per business sector.

## Invalid Numbers

For invalid numbers, the API replies with a bad request status code. A number is considered valid if it contains only digits, an optional leading `+` and whitespace anywhere except immediately after the `+`. A valid number has exactly 3 digits, or more than 6 and less than 13. `00` is acceptable as replacement for the leading `+`.

### Examples of valid numbers

```
+123
00134
+1 23
0033 2
+1234567
001234567
+123456 7
00123456765432
+12345678
```

### Examples of invalid numbers

```
+1234
0012a
00 134
+ 1 23
0033 2 4 5
+1234563456787
```

## Phone Aggregator API - Endpoints

POST ``/aggregate`` - I would recommend using ``/v1/aggregate`` here (It's important to think about API versioning)!

```
curl -s --max-time 5 -d '["+1983248", "001382355", "+147 8192", "+4439877"]' 'http://${HOST}:8080/aggregate' -H 'Content-Type: application/json' -H 'Accept: application/json'
```

Response: 200 ok

```
{"1":{"Technology":2,"Clothing":1},"44":{"Banking":1}}
```

Response 400 Bad request

```
{"message":"no valid number found"}}
```

Response 500 Internal Server Error

```
{"message":"problem trying to get prefixes"}}
```
```
{"message":"Unable to perform integration with sector API"}}
```

# Built With

This application was developed using the following technologies:

* Java 11
* Maven 3.6.3
* Spring-Boot 2.6.0
* Lombok 1.18.22
* Docker 20.10.8

### Getting Started

In the root folder execute:

1. Build project:

```
mvn clean install
```

2. Run - create a docker container - Port: ``8080``

```
docker-compose up web
```

Enjoy, I hope I lived up to expectations! :)