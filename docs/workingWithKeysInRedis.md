# Working with Keys in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "keys". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "keys", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [del](#del)    | Delete a key |
| [exists](#exists)    | Determine if a key exists |
| [expire](#expire)    | Set a key's time to live in seconds |
| [expireAt](#expireAt)    | Set the expiration for an existing key as a UNIX timestamp |
| [keys](#keys)    | Find all keys matching the given pattern |
| [randomKey](#randomKey)    | Return a random key from the keyspace |
| [rename](#rename)    | Rename a key |
| [renamenX](#renamenX)    | Rename a key, only if the new key does not exist |
| [ttl](#ttl)    | Get the time to live for a key |
| [type](#type)    | Determine the type stored at key |

### Operation details
This section provides details on the operations.

#### Del
The del operation is used to delete a key.

**del**
```xml
<redis.del>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.del>
```

**Properties**
* redisKey: The name of the key to delete.

**Sample request**

Following is a sample REST/JSON request that can be handled by the del operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/del](https://redis.io/commands/del)

#### Exists
The exists operation is used to determine if a key exists.

**exists**
```xml
<redis.exists>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.exists>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the exists operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/exists](https://redis.io/commands/exists)

#### Expire
The expire operation is used to set a key's time to live in seconds.

**expire**
```xml
<redis.expire>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisSeconds>{$ctx:redisSeconds}</redisSeconds>
</redis.expire>
```

**Properties**
* redisKey: The name of the key.

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

#### ExpireAt
The expireAt operation is used to set the expiration for an existing key as a UNIX timestamp.

**expireAt**
```xml
<redis.expireAt>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisUnixTime>{$ctx:redisUnixTime}</redisUnixTime>
</redis.expireAt>
```

**Properties**
* redisKey: The name of the key.
* redisUnixTime: The name of the key.

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

#### Keys
The keys operation is used to find all keys matching the given pattern.

**keys**
```xml
<redis.keys>
    <redisPattern>{$ctx:redisPattern}</redisPattern>
</redis.keys>
```

**Properties**
* redisPattern: The pattern to search.

**Sample request**

Following is a sample REST/JSON request that can be handled by the keys operation.

```json
{
    "redisPattern":"*"
}
```

**Related redis documentation**

[https://redis.io/commands/keys](https://redis.io/commands/keys)

#### RandomKey
The randomKey operation is used to return a random key from the keyspace.

**randomKey**
```xml
<redis.randomKey/>
```

**Sample request**

Sample request with empty body can be handled by the randomKey operation.

**Related redis documentation**

[https://redis.io/commands/randomkey](https://redis.io/commands/randomkey)

#### Rename
The rename operation is used to rename a key.

**rename**
```xml
<redis.rename>
    <redisOldKey>{$ctx:redisOldKey}</redisOldKey>
    <redisNewKey>{$ctx:redisNewKey}</redisNewKey>
</redis.rename>
```

**Properties**
* redisOldKey: The name of the old key.
* redisNewKey: The name of the new key.

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

#### RenamenX
The renamenX operation is used to rename a key, only if the new key does not exist.

**renamenX**
```xml
<redis.renamenX>
    <redisOldKey>{$ctx:redisOldKey}</redisOldKey>
    <redisNewKey>{$ctx:redisNewKey}</redisNewKey>
</redis.renamenX>
```

**Properties**
* redisOldKey: The name of the old key.
* redisNewKey: The name of the new key.

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

#### Ttl
The ttl operation is used to get the time to live for a key.

**ttl**
```xml
<redis.ttl>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.ttl>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the ttl operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/ttl](https://redis.io/commands/ttl)

#### Type
The type operation is used to determine the type stored at key.

**type**
```xml
<redis.type>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.type>
```

**Properties**
* redisKey: The name of the key.

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
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the del operation. The sample request for this proxy can be found in [del sample request](#request). You can use this sample as a template for using other operations in this category.

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