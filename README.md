# ☕ cae-utils-mapped-exceptions
Welcome to the repository for the open source CAE Mapped Exceptions library!

### ▶️ The artifact:
```xml
<dependency>
    <groupId>com.clean-arch-enablers</groupId>
    <artifactId>mapped-exceptions</artifactId>
    <version>${version}</version>
</dependency>
```

### 💡 The concept:

Mapped Exceptions represent problems you know might happen during executions of your application's use cases. When calling an external service with an HTTP Client it is known that a 404 response might be retrieved, so a Mapped Exception could be created to represent this specific scenario. 

Mapped Exceptions come in 3 flavors, each one is about a common problem scenario:

- When something goes wrong and it is an internal problem (library APIs being misused)
- When the problem was the input received from external scopes (service APIs being misused)
- When the problem is about something not being found

Each flavor is a subtype:

![image](https://github.com/clean-arch-enablers-project/cae-framework/assets/60593328/64385f5d-7d7e-4b0d-9fc1-77f62399a572)

💡So, for example, If you are developing a REST API with Springboot, you could use your ```@ControllerAdvice``` to map, with the ```@ExceptionHandler``` methods, the ```NotFoundMappedException``` to return a 404 status code, the ```InputMappedException``` to return a 400 status code, and the ```InternalMappedException``` to return a 500. This way any exception extending the ```NotFoundMappedException``` would automatically return a 404, the ones extending the ```InputMappedException``` would return a 400 and the ```InternalMappedException``` ones a 500. No need to specify each type (_UserNotFoundException_, _CreditCardNotFoundException_, etc.) unless there is a good reason for it.
