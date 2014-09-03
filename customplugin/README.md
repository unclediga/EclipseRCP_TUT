*Тюториал в 24 урока от автора "Eclipse 3.0 KICK START" Carlos Valcarcel* 
*[Блог c тюториалом](http://cvalcarcel.wordpress.com/)* __http://cvalcarcel.wordpress.com/__
-------------------------
* Writing an Eclipse Plug-in (Part 2): Creating a custom project in Eclipse – Adding to the New Project Wizard.
Создаём пустой Wizard, перспективу и вьюшку.
Используется интересный класс: 
public class NewWizardMessages extends NLS {
	private static final String BUNDLE_NAME = "customplugin.wizards.messages"; //$NON-NLS-1$
	...
        NLS.initializeMessages(BUNDLE_NAME, NewWizardMessages.class);
    ...    
Похоже с его помощью из файла _messages.properties_ заполняются одноименные поля в этом классе.

-----------------------------
* Writing an Eclipse Plug-in (Part 3): Create a custom project in Eclipse – New Project Wizard: Time to Refactor.

Как раз в этой части приведён пример *Eclipse Externalize Strings mechanism*.
Создаётся отдельный класс NewWizardMessages, который заполняется во время первого 
обращения из файла messages.properties.
Попробовал на "Test"








