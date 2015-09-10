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
  data structure. What does it look familiar?
  
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

> It is of course important not to change the semantics of the internal
  format, but as you see we're fully open to adapt to the change.
  
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
  
_Perfect! This looks great, so how do I get started?_

> Well for now, you need to build the dependency yourself. It's all very new
  and so. But there will be a Maven artifact in the Sonatype repository some
  time in the near future.
  
_No problem. I can just `mvn install` right?_

> Yes, that should do it.

_Thank you! Finally, decoupling and services without the problem of data
 transfer formats._
 
> Glad I could help. And happy hacking!

[1]: http://martinfowler.com/bliki/TolerantReader.html
