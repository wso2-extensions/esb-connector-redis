# Working with Sets in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "sets". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "sets", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [sadd](#sadd)    | Add one or more members to a set |
| [sDiffStore](#sDiffStore)    | Subtract multiple sets and store the resulting set in a key |
| [sInter](#sInter)    | Intersect multiple sets |
| [sInterStore](#sInterStore)    | Intersect multiple sets and store the resulting set in a key |
| [sIsMember](#sIsMember)    | Determine if a given value is a member of a set |
| [sMembers](#sMembers)    | Get the all members in a set |
| [sMove](#sMove)    | Move a number from one set to another |
| [sPop](#sPop)    | Remove and return one or multiple random members from a set |
| [sRandMember](#sRandMember)    | Get one or multiple random members from a set |
| [sRem](#sRem)    | Remove one or more members from a set |
| [sUnion](#sUnion)    | Add multiple sets |
| [sUnionStore](#sUnionStore)    | Add multiple sets and store the resulting set in a key |

### Operation details
This section provides details on the operations.

#### Sadd
The sadd operation is used to add one or more members to a set.

**sadd**
```xml
<redis.sadd>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisMembers>{$ctx:redisMembers}</redisMembers>
</redis.sadd>
```

**Properties**
* redisKey: The name of the key.
* redisMembers: The value to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sadd operation.

```json
{
    "redisKey":"sampleKey",
    "redisMembers":"sampleValues"
}
```

**Related redis documentation**

[https://redis.io/commands/sadd](https://redis.io/commands/sadd)

#### SDiffStore
The sDiffStore operation is used to subtract multiple sets and store the resulting set in a key.

**sDiffStore**
```xml
<redis.sDiffStore>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
</redis.sDiffStore>
```

**Properties**
* redisKey: The name of the key.
* redisDstkey: The name of the destination key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sDiffStore operation.

```json
{
    "redisKey":"sampleKey",
    "redisDstkey":"sampleDestinationKey"
}
```

**Related redis documentation**

[https://redis.io/commands/sdiffstore](https://redis.io/commands/sdiffstore)

#### SInter
The sInter operation is used to intersect multiple sets.

**sInter**
```xml
<redis.sInter>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.sInter>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sInter operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/sinter](https://redis.io/commands/sinter)

#### SInterStore
The sInterStore operation is used to intersect multiple sets and store the resulting set in a key.

**sInterStore**
```xml
<redis.sInterStore>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
</redis.sInterStore>
```

**Properties**
* redisKey: The name of the key.
* redisDstkey: The name of the destination key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sInterStore operation.

```json
{
    "redisKey":"sampleKey",
    "redisDstkey":"sampleDestinationKey"
}
```

**Related redis documentation**

[https://redis.io/commands/sinterstore](https://redis.io/commands/sinterstore)

#### SIsMember
The sIsMember operation is used to determine if a given value is a member of a set.

**sIsMember**
```xml
<redis.sIsMember>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisMember>{$ctx:redisMember}</redisMember>
</redis.sIsMember>
```

**Properties**
* redisKey: The name of the key.
* redisMember: The name of a member in a key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sIsMember operation.

```json
{
    "redisKey":"sampleKey",
    "redisMember":"sampleMember"
}
```

**Related redis documentation**

[https://redis.io/commands/sismember](https://redis.io/commands/sismember)

#### SMembers
The sMembers operation is used to get the all members in a set.

**sMembers**
```xml
<redis.sMembers>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.sMembers>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sMembers operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/smembers](https://redis.io/commands/smembers)

#### SMove
The sMove operation is used to move a member from one set to another.

**sMove**
```xml
<redis.sMove>
    <redisSrckey>{$ctx:redisSrckey}</redisSrckey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
    <redisMember>{$ctx:redisMember}</redisMember>
</redis.sMove>
```

**Properties**
* redisSrckey: The name of the source key.
* redisDstkey: The name of the destination key.
* redisMember: The name of the member.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sMove operation.

```json
{
    "redisSrckey":"sampleSourceKey",
    "redisDstkey":"sampleDestinationKey",
    "redisMember":"sampleMember"
}
```

**Related redis documentation**

[https://redis.io/commands/smove](https://redis.io/commands/smove)

#### SPop
The sPop operation is used to remove and return one or multiple random members from a set.

**sPop**
```xml
<redis.sPop>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.sPop>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sPop operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/spop](https://redis.io/commands/spop)

#### SRandMember
The sRandMember operation is used to get one or multiple random members from a set.

**sRandMember**
```xml
<redis.sRandMember>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.sRandMember>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sRandMember operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/srandmember](https://redis.io/commands/srandmember)

#### SRem
The sRem operation is used to remove one or more members from a set.

**sRem**
```xml
<redis.sRem>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisMembers>{$ctx:redisMembers}</redisMembers>
</redis.sRem>
```

**Properties**
* redisKey: The name of the key.
* redisMembers: The members which want to remove.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sRem operation.

```json
{
    "redisKey":"sampleKey",
    "redisMembers":"sampleMember1 sampleMember2"
}
```

**Related redis documentation**

[https://redis.io/commands/srem](https://redis.io/commands/srem)

#### SUnion
The sUnion operation is used to add multiple sets.

**sUnion**
```xml
<redis.sUnion>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.sUnion>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sUnion operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/sunion](https://redis.io/commands/sunion)

#### SUnionStore
The sUnionStore operation is used to add multiple sets and store the resulting set in a key.

**sUnionStore**
```xml
<redis.sUnionStore>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
</redis.sUnionStore>
```

**Properties**
* redisKey: The name of the key.
* redisDstkey: The name of the destination key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the sUnionStore operation.

```json
{
    "redisKey":"sampleKey",
    "redisDstkey":"sampleDestinationKey"
}
```

**Related redis documentation**

[https://redis.io/commands/sunionstore](https://redis.io/commands/sunionstore)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the sadd operation. The sample request for this proxy can be found in [sadd sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="sadd"
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
         <property expression="json-eval($.redisMembers)" name="redisMembers"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.sadd>
            <redisKey>{$ctx:redisKey}</redisKey>
            <redisMembers>{$ctx:redisMembers}</redisMembers>
         </redis.sadd>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                                
```