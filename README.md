# vulcano-health-check-monitor

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/ryanpadilha/vulcano-health-check-monitor.png)](https://travis-ci.org/ryanpadilha/vulcano-health-check-monitor)
[ ![Download](https://api.bintray.com/packages/ryanpadilha/maven/vulcano-health-check-monitor/images/download.svg) ](https://bintray.com/ryanpadilha/maven/vulcano-health-check-monitor/_latestVersion)

Vulcano Health Check API Monitor for Microservices.<br>
This application consume exposed endpoints for monitoring services on SOA.<br><br>
The goal is include additional features for monitoring and manage applications when it's on production environment.<br>
Basically we check, on this version, four endpoints:

- /health
- /info
- /properties
- /environment

See more details on [vulcano-health-core](https://github.com/ryanpadilha/vulcano-health-core).

### Requirements

- Java 1.8 or later.
- Maven 3.x or later.
- PostgreSQL Server 9.x or later.

## Execution

To execute this Spring Boot Application just run the statement as follow:

```
java -jar vulcano-health-check-monitor.jar
```

The server is running on [http://localhost:9192/](http://localhost:9192/)

## License

Copyright 2017 © ResearchLabs under [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```