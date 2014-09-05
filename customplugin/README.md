**Тюториал в 24 урока от автора "Eclipse 3.0 KICK START" Carlos Valcarcel** 

*[Блог c тюториалом](http://cvalcarcel.wordpress.com/)* __http://cvalcarcel.wordpress.com/__

-------------------------
**Writing an Eclipse Plug-in (Part 2): Creating a custom project in Eclipse – Adding to the New Project Wizard.**

Создаём пустой Wizard, перспективу и вьюшку.
Используется интересный класс:
```java
public class NewWizardMessages extends NLS {
	private static final String BUNDLE_NAME = "customplugin.wizards.messages"; //$NON-NLS-1$
	//...
        NLS.initializeMessages(BUNDLE_NAME, NewWizardMessages.class);
    //...    
}    
``` 
Похоже с его помощью из файла _messages.properties_ заполняются одноименные поля в этом классе.

-----------------------------
**Writing an Eclipse Plug-in (Part 3): Create a custom project in Eclipse – New Project Wizard: Time to Refactor.**

Как раз в этой части приведён пример *Eclipse Externalize Strings mechanism*.
Создаётся отдельный класс NewWizardMessages, который заполняется во время первого 
обращения из файла messages.properties.
Попробовал на "Test"

-------------------------------
**Writing an Eclipse Plug-in (Part 4): Create a Custom Project in Eclipse – New Project Wizard: the Behavior**

- Тесты добавили. У меня что-то пока не проходит :((
- В классе `CustomProjectSupport` реализована работа с ресурсами: создание проекта.
Всё делается через `ResourcesPlugin.getWorkspace()...`. Как говорится в _help_, 
все действия с ресурсами будут проходить через `ResourcesPlugin`.
- Напомнил работу с созданием массива из уже имеющегося:
```java
    String[] prevNatures = description.getNatureIds();
    String[] newNatures = new String[prevNatures.length + 1];
    System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
    newNatures[prevNatures.length] = ProjectNature.NATURE_ID;
```


-------------------------------
**Writing an Eclipse Plug-in (Part 5): Adding Icons and A New Project Structure**

What did we do this time around?

1. Added icons to the Custom Project and Custom Perspective.
- Created a default folder structure at project creation.
- Created a new plug-in and add a custom navigator to it.
- Removed the custom navigator from the customplugin.
- Integrated the custom navigator with the original plug-in.





