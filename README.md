[![Build Status](https://travis-ci.org/garethahealy/elastoplast.svg?branch=master)](https://travis-ci.org/garethahealy/elastoplast)
[![Release Version](https://img.shields.io/maven-central/v/com.garethahealy.elastoplast/elastoplast-parent.svg?maxAge=2592000)](https://mvnrepository.com/artifact/com.garethahealy.elastoplast/elastoplast-parent)
[![License](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)]()

# elastoplast
PoC to create a docker image for ElastAlert and to understand how it works.

- https://github.com/Yelp/elastalert/tree/master/elastalert
- http://engineeringblog.yelp.com/2015/10/elastalert-alerting-at-scale-with-elasticsearch.html
- http://engineeringblog.yelp.com/2016/03/elastalert-part-two.html

## Building
- mvn clean install
- docker build --tag garethahealy/elastoplast-base base/src/main/docker/
- docker build --tag garethahealy/elastoplast-rule1 rule1/src/main/docker/

## Running
elastic-load is a simple Apache Camel application which hosts an embedded ElasticSearch server. To run this application simply run:
- cd elastic-load && mvn camel:run

Once it has started, you can copy an example JSON file from elastic-loader/data/app.json into elastic-loader/target/json.
The data will be parsed and loaded into ElasticSearch.

The next step is to start Kibana and check the data is loaded. When asked for the index name, set it to: oselogs*
If you cannot see any data, check the timeframe (top right corner) is set to "Last 5 Years"

The next step is to update the rule1 config.yaml so that the docker image can see the ElasticSearch application.
You will need to update 'es_host' to the same IP as en0 (i.e.: ifconifg / ip addr show).
Once thats complete, rebuild the rule1 image and run it. You should see an event match printed to the console

- docker build --tag garethahealy/elastoplast-rule1 rule1/src/main/docker/
- docker run --net=host garethahealy/elastoplast-rule1

## Whats next?
Play with this some more!

