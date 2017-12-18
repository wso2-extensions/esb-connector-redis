# Working with Hashes in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "hashes". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "connection", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [hDel](#hDel)    | Delete one or more hash fields |
| [hExists](#hExists)    | Determine if a hash field exists |
| [hGet](#hGet)    | Get the value of a hash field |
| [hGetAll](#hGetAll)    | Get all the fields and values in a hash |
| [hIncrBy](#hIncrBy)    | Increment the integer value of a hash field by the given number |
| [hKeys](#hKeys)    | Get all the fields in a hash |
| [hLen](#hLen)    | Get the number of fields in a hash |
| [hMGet](#hMGet)    | Get the values of all the given hash fields |
| [hMSet](#hMSet)    | Set multiple hash fields to multiple values |
| [hSet](#hSet)    | Set the string value of a hash field |
| [hSetnX](#hSetnX)    | Set the value of a hash field, only if the field does not exist |
| [hVals](#hVals)    | Get all the values in a hash |

### Operation details
This section provides details on the operations.

#### HDel
The hDel operation is used to delete one or more hash fields.

**hDel**
```xml
<redis.hDel>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFields>{$ctx:redisFields}</redisFields>
</redis.hDel>
```

**Properties**
* redisKey: The name of the key.
* redisFields: The values of the fields, which want to delete.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hDel operation.

```json
{
    "redisKey":"sampleKey",
    "redisFields":"sampleField1 sampleField2"
}
```

**Related redis documentation**

[https://redis.io/commands/hdel](https://redis.io/commands/hdel)

#### HExists
The hExists operation is used to determine if a hash field exists.

**hExists**
```xml
<redis.hExists>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
</redis.hExists>
```

**Properties**
* redisKey: The name of the key.
* redisField: The value of field.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hExists operation.

```json
{
    "redisKey":"sampleKey",
    "redisField":"sampleField"
}
```

**Related redis documentation**

[https://redis.io/commands/hexists](https://redis.io/commands/hexists)

#### HGet
The hGet operation is used to get the value of a hash field.

**hGet**
```xml
<redis.hGet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
</redis.hGet>
```

**Properties**
* redisKey: The name of the key.
* redisField: The value of field.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hGet operation.

```json
{
    "redisKey":"sampleKey",
    "redisField":"sampleField"
}
```

**Related redis documentation**

[https://redis.io/commands/hget](https://redis.io/commands/hget)

#### HGetAll
The hGetAll operation is used to get all the fields and values in a hash.

**hGetAll**
```xml
<redis.hGetAll>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hGetAll>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hGetAll operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hgetall](https://redis.io/commands/hgetall)

#### HIncrBy
The hIncrBy operation is used to increment the integer value of a hash field by the given number.

**hIncrBy**
```xml
<redis.hIncrBy>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hIncrBy>
```

**Properties**
* redisKey: The name of the key.
* redisField: The value of field.
* redisValue: The increment value.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hIncrBy operation.

```json
{
    "redisKey":"sampleKey",
    "redisField":"sampleField",
    "redisValue":"1"
}
```

**Related redis documentation**

[https://redis.io/commands/hincrby](https://redis.io/commands/hincrby)

#### HKeys
The hKeys operation is used to get all the fields in a hash.

**hKeys**
```xml
<redis.hKeys>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hKeys>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hKeys operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hkeys](https://redis.io/commands/hkeys)

#### HLen
The hLen operation is used to get the number of fields in a hash.

**hLen**
```xml
<redis.hLen>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hLen>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hLen operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hlen](https://redis.io/commands/hlen)

#### HMGet
The hMGet operation is used to get the values of all the given hash fields.

**hMGet**
```xml
<redis.hMGet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFields>{$ctx:redisFields}</redisFields>
</redis.hMGet>
```

**Properties**
* redisKey: The name of the key.
* redisFields: The values of the fields.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hMGet operation.

```json
{
    "redisKey":"sampleKey",
    "redisFields":"sampleField1 sampleField2"
}
```

**Related redis documentation**

[https://redis.io/commands/hmget](https://redis.io/commands/hmget)

#### HMSet
The hMSet operation is used to set multiple hash fields to multiple values.

**hMSet**
```xml
<redis.hMSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFieldsValues>{$ctx:redisFieldsValues}</redisFieldsValues>
</redis.hMSet>
```

**Properties**
* redisKey: The name of the key.
* redisFieldsValues: the values of fields, which want to add to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hMSet operation.

```json
{
    "redisKey":"sampleKey",
    "redisFieldsValues":"sampleField1 sampleValue1 sampleField2 sampleValue2"
}
```

**Related redis documentation**

[https://redis.io/commands/hmset](https://redis.io/commands/hmset)

#### HSet
The hSet operation is used to set the string value of a hash field.

**hSet**
```xml
<redis.hSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hSet>
```

**Properties**
* redisKey: The name of the key.
* redisField: The value of field.
* redisValue: The value which set to the field.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hSet operation.

```json
{
    "redisKey":"sampleKey",
    "redisField":"sampleField",
    "redisValue":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/hset](https://redis.io/commands/hset)

#### HSetnX
The hSetnX operation is used to set the value of a hash field, only if the field does not exist.

**hSetnX**
```xml
<redis.hSetnX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hSetnX>
```

**Properties**
* redisKey: The name of the key.
* redisField: The value of field.
* redisValue: The value which set to the field.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hSetnX operation.

```json
{
    "redisKey":"sampleKey",
    "redisField":"sampleField",
    "redisValue":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/hsetnx](https://redis.io/commands/hsetnx)

#### HVals
The hVals operation is used to get all the values in a hash.

**hVals**
```xml
<redis.hVals>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hVals>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hVals operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hvals](https://redis.io/commands/hvals)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the hDel operation. The sample request for this proxy can be found in [hDel sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="hDel"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.redisHost)" name="redisHost"/>
         <property expression="json-eval($.redisPort)" name="redisPort"/>
         <property expression="json-eval($.redisTimeout)" name="redisTimeout"/>
         <property expression="json-eval($.redisKey)" name="redisKey"/>
         <property expression="json-eval($.redisFields)" name="redisFields"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.hDel>
            <redisKey>{$ctx:redisKey}</redisKey>
            <redisFields>{$ctx:redisFields}</redisFields>
         </redis.hDel>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                               
```