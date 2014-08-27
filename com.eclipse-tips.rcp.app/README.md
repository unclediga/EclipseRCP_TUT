Tutorial from Prakash "Command Framework Tutorial".

Commands Part 5: Authentication in RCP applications
http://blog.eclipse-tips.com/2009/02/commands-part-5-authentication-in-rcp.html

В менюшке My Menu создали пункт Login/Logout.
В зависимости состояния подключения пользователя,
кнопочка подписана либо  Login, либо Logout.
В меню расмещена одна общяя команда с двумя обработчиками.
Создан SourceServeceProvider, который для Expression Framework 
поставляет переменную sessionState.
На оба обработчика в activeWhen подцеплено условие по этой переменной.
Они поочередно становятся активными.

Плюс интересное дополнение к обработчику. Они реализуют IElementUpdater,
т.е. могут влиять на элемент, их вызвавший. 
До конца не разобрался.


   