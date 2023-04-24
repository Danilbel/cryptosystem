$(document).ready(function () {
    // При загрузке страницы проверяем поля на пустоту и добавляем класс disabled
    checkFields();

    // Проверяем поля на пустоту при изменении их значений
    $('[data-check-field]').on('input', checkFields);


    $('#crypto-form').submit(function () {
        checkFields();

        if ($('#button-encrypt, #button-decrypt').hasClass('disabled')) {
            return false;
        }
    });

    // Обработка открытия файла
    $('#open').on('change', function (e) {
        let file = e.target.files[0];
        if (file) {
            let reader = new FileReader();
            reader.onload = function () {
                let fileContentText = reader.result;
                $('#editor-textarea').val(fileContentText);
                checkFields();
            }
            reader.readAsText(file);
        }
    });

    // Обработка очистки поля
    $('#clear').click(function () {
        $('#editor-textarea').val('');
        checkFields();
    });

    // Обработка сохранения поля в файл
    $('#save').click(function () {
        let content = $('#editor-textarea').val();
        let blob = new Blob([content], {type: "text/plain;charset=utf-8"});
        let link = document.createElement("a");
        link.download = "file.txt";
        link.href = URL.createObjectURL(blob);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    });

    // Для Тритемиуса обработка Радио кнопки
    $('[data-check-radio]').on('change', function () {
        var selectedId = $(this).attr('id');
        addFormActionAttr(selectedId);
        $('.input-key').addClass('display-none');
        if (selectedId === 'linear') {
            $('#key-a').removeClass('display-none');
            $('#key-b').removeClass('display-none');
        } else if (selectedId === 'quadratic') {
            $('#key-a').removeClass('display-none');
            $('#key-b').removeClass('display-none');
            $('#key-c').removeClass('display-none');
        } else if (selectedId === 'slogan') {
            $('#key-slogan').removeClass('display-none');
        }
        checkFields();
    });
});

function addFormActionAttr(value) {
    const en = '/tritemius/encrypt-result';
    const de = '/tritemius/decrypt-result';

    $('#button-encrypt').attr('formaction', en + '-' + value);
    $('#button-decrypt').attr('formaction', de + '-' + value);
}

function checkFields() {
    let allKeysFilled = true;
    $('[data-check-key]').each(function () {
        if ($(this).val() === '' && !$(this).hasClass('display-none')) {
            allKeysFilled = false;
            return false; // Выход из цикла each, если найден пустой элемент
        }
    });

    // Проверяем текстовое поле и поле ключа на пустоту
    if ($('#editor-textarea').val() === '' || !allKeysFilled) {
        // Добавляем класс disabled к кнопкам encrypt и decrypt
        $('#button-encrypt, #button-decrypt').addClass('disabled');
    } else {
        // Удаляем класс disabled у кнопок encrypt и decrypt
        $('#button-encrypt, #button-decrypt').removeClass('disabled');
    }

    // Проверяем текстовое поле на пустоту
    if ($('#editor-textarea').val() === '') {
        // Добавляем класс disabled к кнопкам clear и save
        $('[data-editor-button]').addClass('disabled');
    } else {
        // Удаляем класс disabled у кнопок clear и save
        $('[data-editor-button]').removeClass('disabled');
    }

    if($('#generate-key-length').val() === ''){
        $('#generate-key').addClass('disabled');
    } else {
        $('#generate-key').removeClass('disabled');
    }
}
