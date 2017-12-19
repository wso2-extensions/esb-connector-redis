# Working with Hashes in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with hashes. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with hashes, see [sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [hDel](#deleting-hash-fields)    | Deletes one or more hash fields |
| [hExists](#determining-the-existence-of-a-hash-field)    | Determines the existence of a hash field |
| [hGet](#retrieving-the-value-of-a-hash-field)    | Retrieves the value of a hash field |
| [hGetAll](#retrieving-all-hash-fields-and-values)    | Retrieves all the fields and values in a hash |
| [hIncrBy](#incrementing-the-integer-value-of-a-hash-field)    | Increments the integer value of a hash field by the specified amount |
| [hKeys](#retrieving-all-the-fields-in-a-hash)    | Retrieves all the fields in a hash |
| [hLen](#retrieving-the-number-of-fields-in-a-hash)    | Retrieves the number of fields in a hash |
| [hMGet](#retrieving-values-of-all-specified-hash-fields)    | Retrieves values of all specified hash fields |
| [hMSet](#setting-specified-fields-to-their-respective-values)    | Sets specified fieldsto their respective values |
| [hSet](#setting-a-specific-field-in-a-hash-to-a-value)    | Sets a specific field in a hash to a specified value |
| [hSetnX](#setting-a-field-to-a-value-only-if-the-field-does-not-exist-in-the-hash)    | Sets a field to a value, only if the field does not alreay exist in the hash |
| [hVals](#retrieving-all-values-in-a-hash)    | Retrieves all values in a hash |

### Operation details
This section provides more details on the operations.

#### Deleting hash fields
The hDel operation deletes one or more specified hash fields.

**hDel**
```xml
<redis.hDel>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFields>{$ctx:redisFields}</redisFields>
</redis.hDel>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisFields: The fields that you want to delete.

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

#### Determining the existence of a hash field
The hExists operation determines whether a specified hash field exists.

**hExists**
```xml
<redis.hExists>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
</redis.hExists>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisField: The field to determine existence.

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

#### Retrieving the value of a hash field
The hGet operation retrieves the value of a particular field in a hash stored in a specified key.

**hGet**
```xml
<redis.hGet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
</redis.hGet>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisField: The field for which you want to retrieve the value.

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

#### Retrieving all hash fields and values 
The hGetAll operation retrieves all the fields and values of a hash stored in a specified key.

**hGetAll**
```xml
<redis.hGetAll>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hGetAll>
```

**Properties**
* redisKey: The name of the key where the hash is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hGetAll operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hgetall](https://redis.io/commands/hgetall)

#### Incrementing the integer value of a hash field
The hIncrBy operation increments the integer value of a hash field by the specified amount.

**hIncrBy**
```xml
<redis.hIncrBy>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hIncrBy>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisField: The hash field for which you want to increment the value.
* redisValue: The amount by which you want to increment the hash field value.

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

#### Retrieving all the fields in a hash
The hKeys operation retrieves all the fields in a hash.

**hKeys**
```xml
<redis.hKeys>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hKeys>
```

**Properties**
* redisKey: The name of the key where the hash is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hKeys operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hkeys](https://redis.io/commands/hkeys)

#### Retrieving the number of fields in a hash
The hLen operation retrieves the number of fields in a hash.

**hLen**
```xml
<redis.hLen>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hLen>
```

**Properties**
* redisKey: The name of the key where the hash is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the hLen operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/hlen](https://redis.io/commands/hlen)

#### Retrieving values of all specified hash fields
The hMGet operation retrieves values associated with each of the specified fields in a hash that is stored in a particular key.

**hMGet**
```xml
<redis.hMGet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFields>{$ctx:redisFields}</redisFields>
</redis.hMGet>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisFields: The fields for which you want to retrieve values.

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

#### Setting specified fields to their respective values 
The hMSet operation sets specified fields to their respective values in the hash stored in a particular key.

**hMSet**
```xml
<redis.hMSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisFieldsValues>{$ctx:redisFieldsValues}</redisFieldsValues>
</redis.hMSet>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisFieldsValues: the fields you want to set and their respective values.

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

#### Setting a specific field in a hash to a value
The hSet operation sets a specific field in a hash to a specified value.

**hSet**
```xml
<redis.hSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hSet>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisField: The field for which you want to set a value.
* redisValue: The value that you want to set for the field.

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

#### Setting a field to a value, only if the field does not exist in the hash
The hSetnX operation sets a specified field to a value, only if the field does not already exist in the hash. If field already exists, this operation has no effect.

**hSetnX**
```xml
<redis.hSetnX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisField>{$ctx:redisField}</redisField>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.hSetnX>
```

**Properties**
* redisKey: The name of the key where the hash is stored.
* redisField: The new field in the hash for which you want to set a value.
* redisValue: The value that you want to set for the new field.

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

#### Retrieving all values in a hash
The hVals operation retrieves all values in a hash that is stored in a particular key.

**hVals**
```xml
<redis.hVals>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.hVals>
```

**Properties**
* redisKey: The name of the key where the hash is stored.

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
Following is a sample proxy service that illustrates how to connect to Redis with the init operation, and then use the hDel operation. The sample request for this proxy can be found in [hDel sample request](#request). You can use this sample as a template for using other operations in this category.

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
