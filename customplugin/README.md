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
- В комментах мужику обясняют, как добавить в меню _New..._ пункт _Custom Project_.

-------------------------------
**Writing an Eclipse Plug-in (Part 5): Adding Icons and A New Project Structure**

What did we do this time around?

1. Added icons to the Custom Project and Custom Perspective.
- Created a default folder structure at project creation.
- Created a new plug-in and add a custom navigator to it.
- Removed the custom navigator from the customplugin.
- Integrated the custom navigator with the original plug-in.

--------------------------------
**Writing an Eclipse Plug-in (Part 6): Adding an Icon To New Project Types**

Воспользовавшись свойствами _Nature_ нашего проекта, сделали так, в корень дерева
отображался собственной иконкой _project_folder.png_.

----------------------------------
**Writing an Eclipse Plug-in (Part 7): Creating a Custom Navigator**

Добрался таки!

- зачем-то создали view-категорию в _customnavigator_
- сделали иконку для _customnavigator_ (из _perspective.png_)
- В _customnavigator_ посмотрели, как можно с помощью мастера externalize strings в `OSGI-INF/I10n/bundle.properties`.
-  

-----------------------------------
**Writing an Eclipse Plug-in (Part 8): Common Navigator: Adding a New Sorter Under navigatorContent**

- Добавил сортировщик для имён категорий, чтоб был порядок Tables->Views->Filters
- Чутка зарефакторили пару классов.

------------------------------------
**Writing an Eclipse Plug-in (Part 9): Custom Project: Defining a Custom File Type**

- Создали свои типы файлов, на основе встроенного в Eclipse xml-типа. По расширению *.xml и содержимому Eclipse определяет, что эти файлики относятся к нашему проекту.
- Ну ещё вынесли оставшиеся строковые константы с помощью Визирда в отдельный properties-файл.


-------------------------------------
**Writing an Eclipse Plug-in (Part 10): Custom Project: Creating a Custom File Type**
- Определили Визарды по созданию новых xml-файликов.
- рефакторили - создали суперкласс CustomProjectNewFile.java 

-------------------------------------
**Writing an Eclipse Plug-in (Part 11): Common Navigator: Displaying Custom Resources or Refresh Or Die or The Magic of navigatorContent**
- Как сделать так, чтобы viewer отражал все изменения, происходящие в Workspace. 
- Изменили Custom Navigator. Вернулись на стандартный класс _org.eclipse.ui.navigator.CommonNavigator_.
- Переделали ContentProvider
  
--------------------------------------
**Writing an Eclipse Plug-in (Part 12): Common Navigator: Keeping the Tree Open When a New Resource is Added**
- При создании нового проекта, у него должно быть показаны открытыми всё ветви подпроектов.


--------------------------------------
**Writing an Eclipse Plug-in (Part 13): Common Navigator: Adding Tests**

Добавили тесты. Пропускаю пока.

--------------------------------------
**Writing an Eclipse Plug-in (Part 14): Common Navigator: Refactoring the Children**
- выделил общий CustomProjectElement

--------------------------------------
**Writing an Eclipse Plug-in (Part 15): Custom Project: Customizing the Perspective Menus (Main menu)**
- Как добавить New Wizard в группы меню "New.." через org.eclipse.ui.perspectiveExtensions.

--------------------------------------
**Writing an Eclipse Plug-in (Part 16): Custom Project: Customizing the Perspective Menus (Toolbar)**
- Добавим три кнопки на главный тулбар декларативно с помощью точки _org.eclipse.ui.menus_ и _Platform Command Framework_.

--------------------------------------
**Writing an Eclipse Plug-in (Part 17): Custom Project: Customizing the Perspective Menus Using Customize Perspective**
- How-To по функциональности менюшки "Window - Customize Perspective..."

--------------------------------------
**Writing an Eclipse Plug-in (Part 18): Common Navigator: Adding Submenus (Presentation)**
- Добавляем popup-menu для навигатора. Настройка визуальной части.

--------------------------------------
**Writing an Eclipse Plug-in (Part 18 – Take 2): Common Navigator: Adding submenus (Presentation/Behavior)**
- менюшки Wizard-во привязываем не через locationURI="popup:common.new.menu?after=additions", а
через расширение контента навигатора:  
point="org.eclipse.ui.navigator.navigatorContent" -> navigatorContent -> commonWizard

--------------------------------------
**Writing an Eclipse Plug-in (Part 19): A Quick Display Fix**
- при наведении мышки на custom navigator title bar, не всплывает tooltip с доп.инфо, как у других вьюшек.
   
--------------------------------------
**Writing an Eclipse Plug-in (Part 20): Return of the Popup Menu (For an Empty Navigator)**
Всё получилось. 
- Выбираешь проект – наикакого popup-меню нет.
- Снимашь выделение и кликаешь в пустом месте "Custom Navigator" – появляется два пункта: "Refresh" и "New…". Последний открывается ещё в два пункта: "Custom Project" – это Wizard, который добавили ради эксперимента, и стандартный "Others…".

--------------------------------------
**Writing an Eclipse Plug-in (Part 21): Return of the Popup Menu (Displaying Resources)**
- Заменили условия доступности для провайдеров. И теперь всё везде видно. И на проекте, и в пустом поле popup-менюшка появляется.

--------------------------------------
**Writing an Eclipse Plug-in (Part 22): Common Navigator: Adding submenus (Presentation)**

- _PART 1_ : Добавил один пункт меню "Schema Table" в popup-меню для навигатора.
- _PART 2_ : Добавил оставшиеся пункты для навигатора.
- Externalize strings

--------------------------------------
**Writing an Eclipse Plug-in (Part 23): Common Navigator: Rewriting History**

Теперь будет новая структура проекта.
- Изменили Unit-test проект для проверки новой структуры;
- Вместо "Deploy" везде пишем "Stored Procedure".

--------------------------------------
**Writing an Eclipse Plug-in (Part 24): Common Navigator: Configuring the submenus (Presentation…again)**

Опять небольшие изменения для popup-менюшки:

Display the proper menu item based on the node/project selected
- If right clicking on Schema then display Table, View and Filter,
- If right clicking on Tables then display Schema Table,
    - If right clicking on Views then display Schema View,
    - If right clicking on Filters then display Schema Filter,
- If right clicking on Stored Procedures then display Stored Procedure.

