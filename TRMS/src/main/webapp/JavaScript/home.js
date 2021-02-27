
function loadProfile(){
  
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 & xhr.status === 200) {
            let employee = JSON.parse(xhr.response)
            console.log(employee)
            if (employee) {
                let profileForm =  document.querySelector('#profile')
                if(profileForm){
                    document.querySelector('#firstName').value = employee.firstName
                    document.querySelector('#lastName').value = employee.lastName
                    document.querySelector('#username').value = employee.userName
                    console.log(employee.username)
                    document.querySelector('#password').value = employee.password
                    document.querySelector('#email').value = employee.email
                    document.querySelector('#phoneNumber').value = employee.phoneNumber
                    document.querySelector('#street').value = employee.street
                    document.querySelector('#city').value = employee.city
                    document.querySelector('#state').value = employee.state
                }
               
            }

        }


    }

    xhr.open('GET', 'http://localhost:8080/TRMS/api/loadHomePage')
    xhr.send()
    

}
function displayName() {
    let btn = document.querySelector('#accountname')
    let title = document.querySelector('#title')
    console.log(btn.innerText)

    let xhr = new XMLHttpRequest()
    console.log(btn)
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 & xhr.status === 200) {
            let employee = JSON.parse(xhr.response)
            console.log(employee)
            if (employee) {

                console.log('---'+employee+'---')
                let firstName = employee.firstName
                let lastName = employee.lastName
                let type = employee.type
                console.log(type)
                btn.innerHTML = firstName + ' ' + lastName + '<i class="fa fa-caret-down"></i>'

                if (type == 1) {
                    title.innerHTML = 'Manager'
                }

                if (type == 2) {
                    title.innerHTML = 'Employee'
                    let registerbtn = document.querySelector('#register')
                    let manageRequestbtn = document.querySelector('#manageRequest')
                    let viewResolvedbtn = document.querySelector('#viewResolvedRequest')
                    registerbtn.style.display = 'none'
                    manageRequestbtn.style.display = 'none'
                    viewResolvedbtn.style.display = 'none'
                }
            }

        }


    }

    xhr.open('GET', 'http://localhost:8080/TRMS/api/loadHomePage')
    xhr.send()
}



window.onload = () => {
    displayName()
    loadProfile()
}


