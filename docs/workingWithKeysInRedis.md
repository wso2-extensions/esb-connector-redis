# Working with Keys in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with keys. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with keys, see [sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [del](#deleting-a-key)    | Deletes a specified key |
| [exists](#determining-the-existence-of-a-key)    | Determines whether a specified key exists |
| [expire](#setting-a-ttl-for-a-key)    | Sets a TTL(Time to live) for a key |
| [expireAt](#setting-an-expiration-for-a-key-in-the-unix-timestamp-format)    | Sets the expiration for an existing key as a UNIX timestamp |
| [keys](#retrieving-keys-that-match-a-specified-pattern)    | Retrieves all keys that match a specified pattern |
| [randomKey](#retrieving-a-random-key-from-the-keyspace)    | Retrieves a random key from the keyspace |
| [rename](#renaming-a-key)    | Renames a key |
| [renamenX](#renaming-a-key-only-if-the-new-key-does-not-already-exist)    | Renames an existing key to a new key, only if the new key does not already exist |
| [ttl](#retrieving-the-ttl-of-a-key)    | Retrieves the TTL(time to live) of a key |
| [type](#retrieving-the-data-type-of-a-value-stored-in-a-key)    | Retrieves the data type of a value stored in a key |

### Operation details
This section provides more details on the operations.

#### Deleting a key
The del operation deletes a specified key if it exists.

**del**
```xml
<redis.del>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.del>
```

**Properties**
* redisKey: The name of the key that you want to delete.

**Sample request**

Following is a sample REST/JSON request that can be handled by the del operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/del](https://redis.io/commands/del)

#### Determining the existence of a key
The exists operation determines whether a specified key exists.

**exists**
```xml
<redis.exists>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.exists>
```

**Properties**
* redisKey: The name of the key to determine existence.

**Sample request**

Following is a sample REST/JSON request that can be handled by the exists operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/exists](https://redis.io/commands/exists)

#### Setting a TTL for a key
The expire operation sets a TTL(Time to live) for a key so that the key will automatically delete once it reaches the TTL. The TTL should be specified in seconds.

**expire**
```xml
<redis.expire>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisSeconds>{$ctx:redisSeconds}</redisSeconds>
</redis.expire>
```

**Properties**
* redisKey: The name of the key for which you want to specify a TTL.
* redisSeconds: The number of seconds representing the TTL that you want to set for the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the expire operation.

```json
{
    "redisKey":"sampleKey",
    "redisSeconds":"10"
}
```

**Related redis documentation**

[https://redis.io/commands/expire](https://redis.io/commands/expire)

#### Setting an expiration for a key in the UNIX timestamp format
The expireAt operation sets the time after which an existing key should expire. Here the time should be specified as a UNIX timestamp.

**expireAt**
```xml
<redis.expireAt>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisUnixTime>{$ctx:redisUnixTime}</redisUnixTime>
</redis.expireAt>
```

**Properties**
* redisKey: The name of the key for which you want to set an expiration.
* redisUnixTime: The time to expire specified in the UNIX timestamp format.

**Sample request**

Following is a sample REST/JSON request that can be handled by the expireAt operation.

```json
{
    "redisKey":"sampleKey",
    "redisUnixTime":"1293840000"
}
```

**Related redis documentation**

[https://redis.io/commands/expireat](https://redis.io/commands/expireat)

#### Retrieving keys that match a specified pattern
The keys operation retrieves all keys that match a specified pattern.

**keys**
```xml
<redis.keys>
    <redisPattern>{$ctx:redisPattern}</redisPattern>
</redis.keys>
```

**Properties**
* redisPattern: The pattern that you want to match when retrieving keys.

**Sample request**

Following is a sample REST/JSON request that can be handled by the keys operation.

```json
{
    "redisPattern":"*"
}
```

**Related redis documentation**

[https://redis.io/commands/keys](https://redis.io/commands/keys)

#### Retrieving a random key from the keyspace
The randomKey operation retrieves a random key from the keyspace.

**randomKey**
```xml
<redis.randomKey/>
```

**Sample request**

A sample request with an empty body can be handled by the randomKey operation.

**Related redis documentation**

[https://redis.io/commands/randomkey](https://redis.io/commands/randomkey)

#### Renaming a key
The rename operation renames an existing key to a new name that is specified.

**rename**
```xml
<redis.rename>
    <redisOldKey>{$ctx:redisOldKey}</redisOldKey>
    <redisNewKey>{$ctx:redisNewKey}</redisNewKey>
</redis.rename>
```

**Properties**
* redisOldKey: The name of an existing key that you want to rename.
* redisNewKey: The new name that you want the key to have.

**Sample request**

Following is a sample REST/JSON request that can be handled by the rename operation.

```json
{
    "redisOldKey":"sampleOldKey",
    "redisNewKey":"sampleNewKey"
}
```

**Related redis documentation**

[https://redis.io/commands/rename](https://redis.io/commands/rename)

#### Renaming a key only if the new key does not already exist 
The renamenX operation renames a key to a new key, only if the new key does not already exist.

**renamenX**
```xml
<redis.renamenX>
    <redisOldKey>{$ctx:redisOldKey}</redisOldKey>
    <redisNewKey>{$ctx:redisNewKey}</redisNewKey>
</redis.renamenX>
```

**Properties**
* redisOldKey: The name of an existing key that you want to rename.
* redisNewKey: The new name that you want the key to have.

**Sample request**

Following is a sample REST/JSON request that can be handled by the renamenX operation.

```json
{
    "redisOldKey":"sampleOldKey",
    "redisNewKey":"sampleNewKey"
}
```

**Related redis documentation**

[https://redis.io/commands/renamenx](https://redis.io/commands/renamenx)

#### Retrieving the TTL of a key
The ttl operation retrieves the TTL(Time to live) value of a specified key.

**ttl**
```xml
<redis.ttl>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.ttl>
```

**Properties**
* redisKey: The name of the key for which you want to retrieve the TTL.

**Sample request**

Following is a sample REST/JSON request that can be handled by the ttl operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/ttl](https://redis.io/commands/ttl)

#### Retrieving the data type of a value stored in a key
The type operation retrieves the data type of a value stored in a specified key.

**type**
```xml
<redis.type>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.type>
```

**Properties**
* redisKey: The name of the key that the value is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the type operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/type](https://redis.io/commands/type)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation, and then use the del operation. The sample request for this proxy can be found in [del sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="del"
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
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.del>
            <redisKey>{$ctx:redisKey}</redisKey>
         </redis.del>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                                
```
