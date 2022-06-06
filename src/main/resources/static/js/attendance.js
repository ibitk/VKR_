$(document).ready(() => {

    $(".plus-minus").change((event) => {
        let isVisited = !event.currentTarget.checked
        let studentId = parseInt(event.currentTarget.getAttribute("data-id"))
        console.log(isVisited)


        $.ajax({
            url: '/api/attendance/' + studentId + '/' + isVisited,         /* Куда пойдет запрос */
            method: 'put',             /* Метод передачи (post или get) */
            dataType: 'html',          /* Тип данных в ответе (xml, json, script, html). */
            success: function (data) {   /* функция которая будет выполнена после успешного запроса.  */

            },
            error: (xhr, err) => {
                event.currentTarget.checked(isVisited)
                alert("Не удалось проставить посещаемость из-за ошибки системы")
            }
        });

    })


})