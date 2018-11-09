# Build POS app on Travis CI
[![Build Status](https://travis-ci.org/omelkova/CI_travis_demo.svg?branch=master)](https://travis-ci.org/omelkova/CI_travis_demo) [![Maintainability](https://api.codeclimate.com/v1/badges/0124869746923ac9d87b/maintainability)](https://codeclimate.com/github/omelkova/CI_travis_demo/maintainability)  [![Test Coverage](https://api.codeclimate.com/v1/badges/0124869746923ac9d87b/test_coverage)](https://codeclimate.com/github/omelkova/CI_travis_demo/test_coverage)

## Build app with Travis CI

 - Sign up to [Travis CI](https://travis-ci.org/) with your Github account.
 - Click the green _Activate_ button, and select the repositories you want to use with Travis CI.
 - Add a `.travis.yml` file with the following content to your repository to tell Travis CI what to do:
 ```
 language: java

jdk:
  - oraclejdk8
 ```
 - commit and push your changes to the github repo. 
 - New build will be triggered

## Run tests and measure code coverage

 - To measure code coverage we will use [JaCoCo gradle plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) 
 - Add following lines to `build.gradle` file:
 ```
 apply plugin: "jacoco"
 <...>
 jacoco {
    toolVersion = "0.8.1"
}

jacocoTestReport {
    reports {
        xml.enabled=true
    }
}
 ```
 - Add script step to the `.travis.yml` file:
 ```
 - gradle test jacocoTestReport
 ```
 - commit these changes. If some of unit tests fail the build becomes red
##  Track code coverage and code quality using [Code climate](https://codeclimate.com)
 - Sign up to [Code climate](http://codeclimate.com/) with your Github account.
 - Activate "Free for open source" account
 - Add Github repository
 - To analyze your code with each commit on Code Climate navigate to `Pero Settings` -> `Test Coverage` -> Copy `TEST_REPORTED_ID` key
 - Add following lines to your `.travis.yml` file:
 ```
 env:
  global:
    - CC_TEST_REPORTER_ID=<your_test_reporter_id>
  before_script:
  - curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-latest-linux-amd64 > ./cc-test-reporter
  - chmod +x ./cc-test-reporter
  - ./cc-test-reporter before-build

script:
 - gradle test jacocoTestReport
 - JACOCO_SOURCE_PATH=src/main/java ./cc-test-reporter format-coverage ./build/reports/jacoco/test/jacocoTestReport.xml --input-type jacoco
 - ./cc-test-reporter upload-coverage -r $CC_TEST_REPORTER_ID
 ```
 - If no mistakes your next build will trigger code coverage analysis on Code climate


