[![Build Status](https://travis-ci.org/olle/tolerant-mapper.svg?branch=master)](https://travis-ci.org/olle/tolerant-mapper)
[![](https://jitpack.io/v/olle/tolerant-mapper.svg)](https://jitpack.io/#olle/tolerant-mapper)

_Hi, have you heard about [TolerantReader][1]?_

> Yes, sure! Why?

_Well, I'm in this project, we're building a solution based on services and
 messaging in order to enforce loose coupling._

> Ok, sounds interesting.

_Yeah, it is. It's just that... well we're trying to come up with a solution
for shared data formats between the end-points._

> I understand.

_And the thing is, we're currently building a lot of libraries with data
transfer object types that we share among all the teams and projects, that
are using the services._

> Hmm... ok, sounds like you are pretty much not loosely coupled at all.

_...well, yeah._

> Too bad.

_...yes._

> But you were asking if I knew about [TolerantReader][1].

_Oh, yes. Well I'm wondering if you could help me to find a solution more
 in that direction?_

> Of course. Have a look at this

    public final class Person {

       @Path("id")
       private Long id;

       @Path("username")
       private String name;

       @Path("details.email")
       private String email;

       @Path("details.phone.work")
       private String phone;

       @Path("details.phone.mobile")
       private String mobile;
    }

> What do you see?

_It's a POJO, with some annotated fields._

> That's right. Now the `Path` annotation describes a mapping to another
  data structure. Does it look familiar?

_Yeah it looks like some kind of object notation format... is it JSON?_

> It could be. Or just a plain map where each dot is a traversal into another
  map, like a tree structure.

_Cool._

> Now the neat thing is that if the external data format changes, we could
  simply update our mapping, like this:

    public final class Person {

       @Path("id")
       private Long id;

       @Path("details.fullName")
       private String name;

       @Path("details.email.primary")
       private String email;

       @Path("details.phone.work")
       private String phone;

       @Path("details.phone.mobile")
       private String mobile;
    }

> It's of course important not to change the semantics of the internal
  representation, but as you see we're fully open to adapt to the
  external format change, without modifying our internal type.

_Wow. Great!_

> It even works with optional data, like this:

    public final class Person {

       @Path("id")
       private Long id;

       @Path("details.fullName")
       private String name;

       @Path("details.email.primary")
       private String email;

       @Path("details.phone.work")
       private String phone;

       @Path("details.phone.mobile")
       private String mobile;

       @Path("details.twitter")
       private Optional<String> twitter;
       @Path("details.web")
       private Optional<String> web;
    }

> Optional fields are perfect for this type of data, without the need to add
  silly `null`-checks all over your code.

> Oh, and check this out - you can reference fields in arrays or collections,
  by index, so if you need to map just some properties of a list, it's easy.

    public final class ContactInfo {

      @Path("user.vcard.phone(3)")
      private String mobile;

      @Path("user.vcard.phone(1)")
      private String work

      @Path("user.vcard.address.lines[0]")
      String street;
      @Path("user.vcard.address.lines[1]")
      String postalCode;
      @Path("user.vcard.address.lines[2]")
      String city;
      @Path("user.vcard.address.lines[3]")
      String country;
    }

_Perfect! This looks great, so how do I get started?_

> You can simply use `tolerant-mapper` as a library in your project. Just
  include it as a Maven dependency from the [Jitpack][3] repository. Then
  simply add the dependency:

```xml
<dependency>
    <!-- From Jitpack-repo -->
    <groupId>com.github.olle</groupId>
    <artifactId>tolerant-mapper</artifactId>
    <version>${SOME-TAG}</version>
</dependency>
```

> Better check out the current [releases][4] for a suitable version though.

_Wow, sounds great! I'm going to make sure to check out [Jitpack][3], and read
 up on how to configure that repository._

_But how do I actually use the `tolerant-mapper` library then?_

> For now, have a look inside the `src/test/java/` directory, look at the
  [`MapperTest`][2]. It describes the general idea, until I get the chance
  to add some more documentation. Ok?

_Thank you! Finally, decoupling and services without the problem of data
 transfer formats._

> Glad I could help. And happy hacking!

[1]: http://martinfowler.com/bliki/TolerantReader.html
[2]: src/test/java/tolerant/mapper/MapperTest.java
[3]: https://jitpack.io
[4]: https://github.com/olle/tolerant-mapper/releases
