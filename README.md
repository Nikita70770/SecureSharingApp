<header>
    <h1>Система защищенного обмена сообщениями в мессенджерах на базе платформы Android</h1>
</header>

<div>
    <h2>Содержание</h2>
    <ul style="font-size: 16px;">
        <li>
            <a href="#annotation">Аннотация</a>
        </li>
        <li>
            <a href="#description">Описание проекта</a>
        </li>
        <li>
            <a href="#technologies">Используемые технологии</a>
        </li>
        <li>
            <a href="#testing">Тестирование проекта</a>
        </li>
</ul>
</div>

<section>
    <h2 id="annotation">Аннотация</h2>
    <p style="text-align:justify;">В выпускной квалификационной работе решается задача разработки системы защищенного обмена сообщениями в мессенджерах для мобильных устройств на базе платформы Android с возможностью шифровки и расшифровки сообщений. Для решения поставленной задачи созданы и объединены в единую систему программные модули, использующие криптографические алгоритмы защищенной передачи данных.
</section>

<section>
    <h2 id="description">Описание проекта</h2>
    <p>Система защищенного обмена в мессенджерах выполнена с целью продемонстрировать обмен информацией между пользователями в реальном времени через сеть Интернет при использовании криптографических алгоритмов защищенной передачи данных.</p>
    <p style="margin-bottom:0;">В разработанной системе выделяются следующие функциональные возможности:</p>
    <ul>
        <li>регистрация/авторизация пользователя в системе;</li>
        <li>обмен контактными данными;</li>
        <li>сохранение полученной контактной информации;</li>
        <li>взаимная аутентификация, относящиеся к двум пользователям;</li>
        <li>выработка общего секретного сеансового ключа;</li>
        <li>генерация псевдослучайных последовательностей;</li>
        <li>формирование таблицы подстановки символов;</li>
        <li>шифровка сообщения перед отправкой пользователю;</li>
        <li>расшифровка полученного от пользователя сообщения. </li>
    </ul>
</section>

<section id="technologies">
    <h2>Используемые технологии</h2>
    <table>
        <tr style="border: 1px solid #fff">
            <td style="border-right:1px solid #fff">Android Studio</td>
            <td style="border-right:1px solid #fff">Java</td>
            <td style="border-right:1px solid #fff">Room Database</td>
            <td>OC Android</td>
        </tr>
    </table>
<section>

<section style="text-align:justify;">
    <h2 id="testing">Тестирование проекта</h2>
    <p style="margin-bottom:0;">Для проведения тестирования реализованной системы, необходимо определить цели, которые должны быть успешно достигнуты:</p>
    <ul>
        <li>
            <a href="#purpose_1">проверить регистрацию и авторизацию пользователя в системе;</a>
        </li>
        <li>
            <a href="#purpose_2">обменяться контактными данными;</a>
        </li>
        <li>
            <a href="#purpose_3">добавить контактные данные;</a>
        </li>
        <li>
            <a href="#purpose_4">проверить взаимную аутентификацию;</a>
        </li>
        <li>
            <a href="#purpose_5">выработать общий секретный сеансовый ключ;</a>
        </li>
        <li>
            <a href="#purpose_6">сгенерировать псевдослучайную последовательность;</a>
        </li>
        <li>
            <a href="#purpose_7">сформировать таблицу подстановки символов;</a>
        </li>
        <li>
            <a href="#purpose_8">выполнить шифрование сообщения;</a>
        </li>
        <li>
            <a href="#purpose_9">выполнить расшифровку сообщения.</a>
        </li>
    </ul>
    <p>В тестировании системы принимали участие пользователи Тиффани и Джейкоб.</p>
    <div>
        <h3 id="purpose_1">Цель тестирования №1</h3>
        <p>Проверить на работоспособность модуль «регистрации и авторизации».</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешная регистрация/авторизация пользователя в системе.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> правильно заполненные поля «Логин» и «Пароль».</p>
        <p style="margin-bottom:0;"><span style="font-weight: bold;">Процедура тестирования:</span> при запуске системы отображается форма регистрации для ввода логина и пароля. Если у пользователя отсутствует учетная запись, он выполняет ввод необходимых данных (логин и пароль) и нажимает на кнопку «Зарегистрироваться». В случае успешной регистрации открывается окно, где отображается пустой список контактов и боковое меню с кнопками «Обменяться данными» и «Добавить контакт».  
Полученный результат (см. рис. 1.1) совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0;">
            <img src="vkr/tests/purpose_1/purpose_1.1.png"/>
            <span></br>Рис. 1.1. Результат теста №1.1</span>
        </div>  
        <p>
        Если у пользователя уже имеется учетная запись, он выполняет ввод необходимых данных и нажимает на кнопку «Войти». В случае успешной авторизации открывается окно, где отображается пустой список контактов и боковое меню. <br/>
Полученный результат (см. рис. 1.2) совпадает с ожидаемым.
        </p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_1/purpose_1.2.png"/>
            <span></br>Рис. 1.2. Результат теста №1.2</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_2">Цель тестирования №2</h3>
        <p>проверить на работоспособность модуль «обмена» контактными данными.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешный обмен контактными данными между пользователями системы.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> правильно заполненные поля «Логин» и «Пароль».</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> при нажатии на кнопку «Обменяться данными» отображается форма, где необходимо ввести следующие данные: логин, пароль и случайно сгенерированное число, которое проставляется в поле для ввода автоматически.
        </p>
        <p>Пользователь Тиффани в открывшейся форме заполняет текстовые поля своими контактными данными. При нажатии пользователем Тиффани на кнопку «Отправить данные» запустится проверка введенной информации на корректность и на соответствие с той информацией, которая уже сохранена в системе после регистрации. В случае успеха отображается список, где Тиффани необходимо выбрать мессенджер, через который она желает отправить свои контактные данные и конкретного пользователя, например Джейкоба.</p>
        <p>Аналогичные шаги выполняет пользователь Джейкоб, который также в открывшейся форме заполняет текстовые поля своими контактными данными и уже после нажимает на кнопку «Отправить данные». В случае успеха Джейкобу отображается список, где ему необходимо выбрать мессенджер, через который он желает отправить свои контактные данные и конкретного пользователя, например Тиффани.</p>
        <p>Полученный результат (см. рис. 1.3) со стороны пользователя Тиффани совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_2/purpose_2.1.png"/>
            <span></br>Рис. 1.3. Результат теста №2.1</span>
        </div>  
        <p>Полученный результат (см. рис. 1.4) со стороны пользователя Джейкоба совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_2/purpose_2.2.png"/>
            <span></br>Рис. 1.4. Результат теста №2.2</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_3">Цель тестирования №3</h3>
        <p>Проверить на работоспособность модуль «добавления» контактных данных.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешное сохранение контакта в системе с последующим отображением в списке чатов.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> правильно скопированная контактная информация от другого пользователя.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> при нажатии на кнопку «Добавить контакт» отображается форма, где необходимо ввести информацию, полученную через мессенджер от конкретного пользователя.</p>
        <p>Пользователь Тиффани в открывшейся форме заполняет текстовое поле контактными данными (логин, пароль и случайно сгенерированное число) другого пользователя, например Джейкоба. При нажатии пользователем Тиффани на кнопку «Сохранить данные» выполняется сохранение контактной информации в системе. В случае успеха у пользователя Тиффани на главном экране в списке чатов появится новый контакт - Джейкоб.</p>
        <p>Аналогичные шаги выполняет пользователь Джейкоб, который также в открывшейся форме заполняет текстовое поле контактными данными другого пользователя, например Тиффани и уже после нажимает на кнопку «Сохранить данные». В случае успеха у пользователя Джейкоба на главном экране в списке чатов появится новый контакт - Тиффани.</p>
        <p>Полученный результат (см. рис. 1.5) со стороны пользователя Тиффани совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_3/purpose_3.1.png"/>
            <span></br>Рис. 1.5. Результат теста №3.1</span>
        </div>  
        <p>Полученный результат (см. рис. 1.6) со стороны пользователя Джейкоба совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_3/purpose_3.2.png"/>
            <span></br>Рис. 1.6. Результат теста №3.2</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_4">Цель тестирования №4</h3>
        <p>проверить на работоспособность модуль взаимной «аутентификации».</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешная взаимная аутентификация, относящиеся к двум сторонам, которые одновременно аутентифицируют друг друга.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> правильно вычисленная хэш-функция на основе случайного числа и пароля пользователя.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> пользователь Тиффани в списке контактов выбирает другого пользователя по имени Джейкоб для того, чтобы убедиться в подлинности проверяемой стороны. После выбора контакта для Тиффани отображается форма для ввода хэш-функции, полученной от Джейкоба. Аналогичная форма, где необходимо ввести хэш-функцию, полученную от Тиффани, отображается и для Джейкоба. В случае успешной взаимной аутентификации открывается окно для общения с пользователем.</p>
        <p>Полученный результат (см. рис. 1.7) со стороны пользователя Тиффани совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_4/purpose_4.1.png"/>
            <span></br>Рис. 1.7. Результат теста №4.1</span>
        </div>  
        <p>Полученный результат (см. рис. 1.8) со стороны пользователя Джейкоба совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_4/purpose_4.2.png"/>
            <span></br>Рис. 1.8. Результат теста №4.2</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_5">Цель тестирования №5</h3>
        <p>проверить на работоспособность модуль «выработки» ключа.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешная выработка общего секретного ключа.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> правильно заполненные поля «значения P и G и значение открытого ключа» и «значение открытого ключа контакта».</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> пользователи (Тиффани и Джейкоб) решают выработать общий секретный ключ при нажатии на кнопку «Установить ключ». Далее пользователи решают между собой кто первым начнет формировать параметры для выработки ключа. Предположим, Тиффани решает это сделать первой нажатием на кнопку «Отправить данные». После нажатия запускается процесс формирования данных и отправки их Джейкобу, который в свою очередь эти данные сохраняет. После сохранения данных Джейкоб формирует свой публичный ключ и отправляет его Тиффани, а уже после нажимает на кнопку «Вычислить секретный ключ». Тиффани вставляет отправленный Джейкобом ключ в поле для вставки значения публичного ключа контакта и также нажимает на кнопку «Вычислить секретный ключ». В случае успеха вырабатывается общий секретный ключ, который будет использоваться в дальнейшем в качестве начального состояния для генератора.</p>
        <p>Полученный результат (см. рис. 1.9) со стороны пользователя Тиффани совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_5/purpose_5.1.png"/>
            <span></br>Рис. 1.9. Результат теста №5.1</span>
        </div>  
        <p>Полученный результат (см. рис. 1.10) со стороны пользователя Джейкоба совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_5/purpose_5.2.png"/>
            <span></br>Рис. 1.10. Результат теста №5.2</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_6">Цель тестирования №6</h3>
        <p>проверить на работоспособность модуль «генерации» псевдослучайных последовательностей.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешно сгенерированная псевдослучайная последовательность.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> в качестве начального значения для генератора используется общий сеансовый ключ.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> после выработки общего секретного ключа запускается процесс формирования псевдослучайной последовательности. По окончании процесса итоговый результат преобразуется в двоичную последовательность.</p>
        <p>Полученный результат (см. рис. 1.11) совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_6/purpose_6.1.png"/>
            <span></br>Рис. 4.11. Результат теста №6</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_7">Цель тестирования №7</h3>
        <p>проверить на работоспособность модуль «формирования» таблицы подстановки символов.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> правильно сформированная таблица.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> сдвиг по таблице, который получается путем преобразования в десятичную систему счисления первых семи символов, образующих двоичную последовательность нулей и единиц из результата работы генератора псевдослучайных последовательностей.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> после того как генератор завершил свою работы запускается процесс формирования таблицы подстановки символов. По окончании процесса создается таблица с общим числом символов – 128 и диапазоном значений в двоичной системе счисления - от 0000000 (т.е. 0) до 0b1111111 (т.е. 127).</p>
        <p>Полученный результат (см. рис. 1.12) совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_7/purpose_7.1.png"/>
            <span></br>Рис. 1.12. Результат теста №7</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_8">Цель тестирования №8</h3>
        <p>проверить на работоспособность модуль «шифрования» сообщения.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешное преобразование исходного сообщения в зашифрованную информацию.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> сообщение пользователя в незашифрованном виде.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> при нажатии на кнопку «Операции» отображается выпадающий список, где необходимо выбрать соответствующую операцию.</p>
        <p>Пользователь Тиффани после формирования сообщения в текстовом поле для Джейкоба нажимает на кнопку «Операции» чтобы открыть выпадающий список и выбрать необходимое действие. В открывшемся списке Тиффани выбирает операцию «Зашифровать» чтобы преобразовать сообщение в зашифрованный вид. После выполнения данной операции исходное сообщение пользователя в текстовом поле заменяется на зашифрованную информацию, которую Тиффани отправляет Джейкобу через конкретный мессенджер при нажатии на кнопку «Отправить».</p>
        <p>Полученный результат (см. рис. 1.13) со стороны пользователя Тиффани совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_8/purpose_8.1.png"/>
            <span></br>Рис. 1.13. Результат теста №8</span>
        </div>  
    </div>
    <div>
        <h3 id="purpose_9">Цель тестирования №9</h3>
        <p>проверить на работоспособность модуль «расшифровки» сообщения.</p>
        <p><span style="font-weight: bold;">Ожидаемый результат:</span> успешное преобразование зашифрованной информации в исходную и читабельную версию сообщения.</p>
        <p><span style="font-weight: bold;">Входные данные:</span> информация от пользователя в зашифрованном виде.</p>
        <p><span style="font-weight: bold;">Процедура тестирования:</span> при нажатии на кнопку «Операции» отображается выпадающий список, где необходимо выбрать соответствующую операцию.</p>
        <p>Пользователь Джейкоб после получения в мессенджере сообщения от Тиффани копирует его в текстовое поле и нажимает на кнопку «Операции» чтобы открыть выпадающий список и выбрать необходимое действие. В открывшемся списке Джейкоб выбирает операцию «Расшифровать» чтобы преобразовать зашифрованную информацию в читабельную версию сообщения. После выполнения данной операции зашифрованная информация, полученная от пользователя и скопированная в текстовое поле заменяется на сообщение в понятном для чтения версии.</p>
        <p>Полученный результат (см. рис. 1.14) со стороны пользователя Джейкоба совпадает с ожидаемым.</p>
        <div align="center" style="margin:10px 0; text-align:center;">
            <img src="vkr/tests/purpose_9/purpose_9.1.png"/>
            <span></br>Рис. 1.14. Результат теста №9</span>
        </div>  
    </div>
</section>