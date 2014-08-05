# JDK-8039776 Patch Agent

The Java Agent that apply monkey patch for [JDK-8039776](https://bugs.openjdk.java.net/browse/JDK-8039776) issue.

## Build

Your can build this agent as followings:

    $ gradle build

The agent jar file will be generated as `build/libs/jdk-8039776-patcher-1.0.jar`.

## Usage

You can use this agent as followings:

    $ java -javaagent:/path/to/jdk-8039776-patcher-1.0.jar ...
