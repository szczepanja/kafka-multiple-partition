# kafka-multiple-partition

Exercise: [kafka consumer group and multiple partitions](https://jaceklaskowski.github.io/kafka-workshop/exercises/kafka-exercise-using-consumer-group.html)

## Conclusions

After creating a topic **t1** with **3 partitions**. What happened?

- I created a topic and assigned three partitions to it
- I created two consumers and two producers (producers are writing to specific partition number `-p 0`)
- Each of the consumers has chosen the producer from which they want to read the messages
- Kafka broker informed me about creating group CG1 and that right now it as 2 members
- After creating another consumer nothing really happened, C1 is still reading from P1 and C2 is reading from P2,
  Consumer 3 is empty
- When I shut down C1, C3 take over his messages

I wanted to find out what will happen when I will write to a partition that doesn't exist?

` kafkacat -P -b :9092 -t t1 -p 5 -K :`

And I got the error:

`Local: Unknown partition`

If I wanted to print messages in IDEA what exactly value and partition I've got message from I needed to shut down all
Consumer consoles and leave only this one in IDEA.

## Glossary:

- **C1/C2...** - Consumer <n>
- **P1/P2...** - Producer <n>
- **topic** - basic unit, actually a name used to describe a group of logs
- **partition** - each topic contains at least one partition, usually several or a dozen or so. In low-level terms, it is a file on the broker's disk to which logs are saved
- **broker** - an Apache Kafka instance, it can be a physical server, a POD in k8s or a virtual machine
- **kafkacat** - is a generic non-JVM producer and consumer for Apache Kafka >=0.8, think of it as a netcat for Kafka.