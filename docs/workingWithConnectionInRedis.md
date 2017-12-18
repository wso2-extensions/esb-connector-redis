# Working with Connection in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "connection". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "connection", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [echo](#echo)    | Echo the given string |
| [ping](#ping)    | Ping the server |
| [quit](#quit)    | Close the connection |

### Operation details
This section provides details on the operations.

#### Echo
The echo operation is used to echo the given string.

**echo**
```xml
<redis.echo>
    <redisMessage>{$ctx:redisMessage}</redisMessage>
</redis.echo>
```

**Properties**
* redisMessage: The message, which we want to print.

**Sample request**

Following is a sample REST/JSON request that can be handled by the append operation.

```json
{
    "redisMessage":"sampleMessage"
}
```

**Related redis documentation**

[https://redis.io/commands/echo](https://redis.io/commands/echo)

#### Ping
The ping operation is used to ping the server.

**ping**
```xml
<redis.ping/>
```

**Sample request**

Sample request with empty body can be handled by the ping operation.

**Related redis documentation**

[https://redis.io/commands/ping](https://redis.io/commands/ping)

#### Quit
The quit operation is used to close the connection.

**quit**
```xml
<redis.quit/>
```

**Sample request**

Sample request with empty body can be handled by the quit operation.

**Related redis documentation**

[https://redis.io/commands/quit](https://redis.io/commands/quit)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the echo operation. The sample request for this proxy can be found in [echo sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="echo"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.redisHost)" name="redisHost"/>
         <property expression="json-eval($.redisPort)" name="redisPort"/>
         <property expression="json-eval($.redisTimeout)" name="redisTimeout"/>
         <property expression="json-eval($.redisMessage)" name="redisMessage"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.echo>
            <redisMessage>{$ctx:redisMessage}</redisMessage>
         </redis.echo>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>
```