
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

    xhr.open('GET', 'http:/http://52.14.14.16/:8080/TRMS/api/loadHomePage')
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
                    let viewEmployeesbtn = document.querySelector('#viewEmployees')
                    registerbtn.style.display = 'none'
                    manageRequestbtn.style.display = 'none'
                    viewResolvedbtn.style.display = 'none'
                    viewEmployeesbtn.style.display = 'none'
                }
            }

        }


    }

    xhr.open('GET', 'http://localhost:8080/TRMS/api/loadHomePage')
    xhr.send()
}


function loadEmployees(){
    console.log('I am inside of loadEmployees')
    let url = 'http://localhost:8080/TRMS/api/getEmployees'
    let tbody = document.getElementById('emplTableData')
    console.log(tbody)
    if(tbody){
        console.log('tbody is selected')
        let xhr = new XMLHttpRequest()

        xhr.onreadystatechange = function(){
            if(xhr.readyState===4 && xhr.status ===200){
                let employees = JSON.parse(xhr.response)
                console.log(employees)

                for(let e of employees){

                    //Create the new elements chich should be appended to the table
                    let tr = document.createElement('tr')
                    let id = document.createElement('td')
                    let name = document.createElement('td')
                    let title = document.createElement('td')
                    let email = document.createElement('td')
                    let manager = document.createElement('td')

                    //Fill out td elements with the data
                    id.innerHTML = e['empId']
                    name.innerHTML = e['firstName'] + ' ' + e['lastName']
                    if(e['type'] == 1){
                        title.innerHTML = 'Manager'
                    }else{
                        title.innerHTML = 'Employee'
                    }
                    email.innerHTML = e['email']
                    if(e['supervisor']){
                        manager.innerHTML =e['supervisor']['firstName'] + ' ' + e['supervisor']['lastName']
                    }
                    else{
                        manager.innerHTML = "NONE"
                        
                    }
                   

                    //Add elements to the table
                    tr.append(id)
                    tr.append(name)
                    tr.append(title)
                    tr.append(email)
                    tr.append(manager)
                    tbody.append(tr)
                }
            }
        }

        xhr.open('GET',url)
        xhr.send()
    }
}


function loadMyApplications(){
    console.log('I am inside of loadMyapplications')
    let url = ' http://localhost:8080/TRMS/api/getMyApplications'
    let tbody = document.getElementById('myReimTableData')
    console.log(tbody)
    if(tbody){
        console.log('tbody is selected')
        let xhr = new XMLHttpRequest()

        xhr.onreadystatechange = function(){
            if(xhr.readyState===4 && xhr.status ===200){
                let applications = JSON.parse(xhr.response)
                console.log(applications)

                for(let a of applications){

                    //Create the new elements chich should be appended to the table
                    let tr = document.createElement('tr')
                    let id = document.createElement('td')
                    let amount = document.createElement('td')
                    let submitTime = document.createElement('td')
                    let status = document.createElement('td')
                    let manager = document.createElement('td')
                    //Fill out td elements with the data
                    id.innerHTML = a['applicationId']
                    amount.innerHTML = a['cost']
                    var n = new Date(a['submitedDate'] )
                    var d = n.toLocaleDateString()
                    submitTime.innerHTML = d + ' ' + a['submitedTime']
                    if(a['status'] == 1){
                        status.innerHTML = 'Pending'
                    }else if(a['status' == 2]){ 
                        status.innerHTML = 'Accepted'
                    }else{ 
                        status.innerHTML = 'Rejected'
                    }
                    manager.innerHTML = a['applicant']['supervisor']['firstName'] + ' ' + a['applicant']['supervisor']['lastName']
                    
                    //Add elements to the table
                    tr.append(id)
                    tr.append(amount)
                    tr.append(submitTime)
                    tr.append(status)
                    tr.append(manager)
                    tbody.append(tr)
                }
            }
        }

        xhr.open('GET',url)
        xhr.send()
    }
}

function loadAllApplications(){
    console.log('I am inside of loadAllapplications')
    let url = 'http://localhost:8080/TRMS/api/getAllApplications'
    let tbody = document.getElementById('allReimTableData')
    console.log(tbody)
    if(tbody){
        console.log('tbody is selected')
        let xhr = new XMLHttpRequest()

        xhr.onreadystatechange = function(){
            if(xhr.readyState===4 && xhr.status ===200){
                let applications = JSON.parse(xhr.response)
                console.log(applications)

                for(let a of applications){

                    //Create the new elements chich should be appended to the table
                    let tr = document.createElement('tr')
                    let id = document.createElement('td')
                    let applicant = document.createElement('td')
                    let amount = document.createElement('td')
                    let submitTime = document.createElement('td')
                    let status = document.createElement('td')
                    let manager = document.createElement('td')

                    let tr1 = document.createElement('tr')
                    let content = document.createElement('td')
                    
                    //Read data from Response
                    let appId = a['applicationId']
                    let applicantName = a['applicant']['firstName'] + ' ' + a['applicant']['lastName']
                    let applicantEmail = a['applicant']['email']
                    let startDate = (new Date(a['startingDate'])).toLocaleDateString()
                    let endDate = (new Date(a['endingDate'])).toLocaleDateString()
                    let appDescription = a['description']
                    let appLocation = a["location"]
                    let appCost = a['cost']
                    let appSubmit = new Date(a['submitedDate']).toLocaleDateString() + ' ' +a['submitedTime']
                    let appStatus = a['status']
                    let appManager = a['applicant']['supervisor']['firstName'] + ' ' + a['applicant']['supervisor']['lastName']

                    //Fill out td elements with the data
                    let idHtml = `<button id="${appId}" class="collapsible" onclick="displayContent(this.id)" >${appId}</button>`
                    id.innerHTML = idHtml
                    applicant.innerHTML =applicantName
                    amount.innerHTML = appCost
                    submitTime.innerHTML = appSubmit
                    if(appStatus == 1){
                        status.innerHTML = 'All'
                    }else if(appStatus == 2){ 
                        status.innerHTML = 'Approved'
                    }else{                    status.innerHTML = 'Denied'
                    }
                    manager.innerHTML = appManager

                    /*Write html to ormat reimbursement content*/
                    let contentHtml = 
                    `<div class="reimContentDiv" id="reimContentDiv${appId}" style="display:none">
                    <h2>Tuition Reimbursement Form</h2>
                    <br><br>
                    <label ><b>Applicant:</b></label>
                    <input disabled type = "text" value="${a['applicant']['firstName']} ${a['applicant']['lastName']}">
                    <br><br>
                    <label><b>Email:</b></label>
                    <input disabled type="email" value = "${applicantEmail}">
                    <br><br>
                    <label><b>Event Start:</b></label>
                    <input type="text" disabled value = "${startDate}">
                    <br><br>
                    <label><b>Event End:</b></label>
                    <input type="text" disabled value = "${endDate}">
                    <br><br>
                    <label><b>Event Location:</b></label>
                    <input type="text" disabled value = "${appLocation}">
                    <br><br>
                    <label ><b>Cost:</b></label>
                    <input type="text" disabled value = "${appCost}">
                    <br><br>
                    <label ><b>Description:</b></label><br><br> 
                    <textarea id="descriptionArea" name="description" disabled>${appDescription}</textarea>
                    <br><br><br><br><br><br>  
                     </div>`
                    
                    
                    content.setAttribute("class","content")
                    content.setAttribute("colspan","6")
                    content.innerHTML = contentHtml;
                    
                    //Add reimbursement list to the table
                    tr.append(id)
                    tr.append(applicant)
                    tr.append(amount)
                    tr.append(submitTime)
                    tr.append(status)
                    tr.append(manager)
                    tbody.append(tr)


                    //Add reimbursement content to the table
                    tr1.append(content)
                    tbody.append(tr1)
                }
            }
        }

        xhr.open('GET',url)
        xhr.send()
    }
}


function loadPendingApplications(){
    console.log('I am inside of loadPendingapplications')
    let url = 'http://localhost:8080/TRMS/api/Pages/getPendingApplications'
    let tbody = document.getElementById('pendingReimTableData')
    console.log(tbody)
    if(tbody){
        console.log('tbody is selected')
        let xhr = new XMLHttpRequest()

        xhr.onreadystatechange = function(){
            if(xhr.readyState===4 && xhr.status ===200){
                let applications = JSON.parse(xhr.response)
                console.log(applications)

                for(let a of applications){

                    //Create the new elements chich should be appended to the table
                    let tr = document.createElement('tr')
                    let id = document.createElement('td')
                    let applicant = document.createElement('td')
                    let amount = document.createElement('td')
                    let submitTime = document.createElement('td')
                    let status = document.createElement('td')
                    let manager = document.createElement('td')

                    let tr1 = document.createElement('tr')
                    let content = document.createElement('td')
                    
                    //Read data from Response
                    let appId = a['applicationId']
                    let applicantName = a['applicant']['firstName'] + ' ' + a['applicant']['lastName']
                    let applicantEmail = a['applicant']['email']
                    let startDate = (new Date(a['startingDate'])).toLocaleDateString()
                    let endDate = (new Date(a['endingDate'])).toLocaleDateString()
                    let appDescription = a['description']
                    let appLocation = a["location"]
                    let appCost = a['cost']
                    let appSubmit = new Date(a['submitedDate']).toLocaleDateString() + ' ' +a['submitedTime']
                    let appStatus = a['status']
                    

                    //Fill out td elements with the data
                    let idHtml = `<button class="collapsible" id="${appId}" onclick="displayContent(this.id)" >${appId}</button>`
                    id.innerHTML = idHtml
                    applicant.innerHTML =applicantName
                    amount.innerHTML = appCost
                    submitTime.innerHTML = appSubmit
                    if(appStatus == 1){
                        status.innerHTML = 'Pending'
                    }else if(appStatus == 2){ 
                        status.innerHTML = 'Approved'
                    }else{            
                        status.innerHTML = 'Denied'
                    }
                 

                    /*Write html to ormat reimbursement content*/
                    let contentHtml = 
                    `<div class="reimContentDiv" id="reimContentDiv${appId}" style="display:none">
                    <h2>Tuition Reimbursement Form</h2>
                    <br><br>
                    <label ><b>Applicant:</b></label>
                    <input disabled type = "text" value="${a['applicant']['firstName']} ${a['applicant']['lastName']}">
                    <br><br>
                    <label><b>Email:</b></label>
                    <input disabled type="email" value = "${applicantEmail}">
                    <br><br>
                    <label><b>Event Start:</b></label>
                    <input type="text" disabled value = "${startDate}">
                    <br><br>
                    <label><b>Event End:</b></label>
                    <input type="text" disabled value = "${endDate}">
                    <br><br>
                    <label><b>Event Location:</b></label>
                    <input type="text" disabled value = "${appLocation}">
                    <br><br>
                    <label ><b>Cost:</b></label>
                    <input type="text" disabled value = "${appCost}">
                    <br><br>
                    <label ><b>Description:</b></label><br><br> 
                    <textarea id="descriptionArea" name="description" disabled>${appDescription}</textarea>
                    <br><br><br><br><br><br>
                    <ul>
                        <li><a href="http://localhost:8080/TRMS/api/Accept-${appId}">Approve</a></li>
                        <li><a href="http://localhost:8080/TRMS/api/Reject-${appId}">Deny</a></li>
                    </ul>   
                     </div>`
                    
                    
                    content.setAttribute("class","content")
                    content.setAttribute("colspan","5")
                    content.innerHTML = contentHtml;
                    
                    //Add reimbursement list to the table
                    tr.append(id)
                    tr.append(applicant)
                    tr.append(amount)
                    tr.append(submitTime)
                    tr.append(status)
                    tr.append(manager)
                    tbody.append(tr)


                    //Add reimbursement content to the table
                    tr1.append(content)
                    tbody.append(tr1)
                }
            }
        }

        xhr.open('GET',url)
        xhr.send()
    }
}



window.onload = () => {
    displayName()
    loadProfile()
    loadEmployees()
    loadMyApplications()
    loadPendingApplications()
    loadAllApplications()
    displayContent()
   
}

function displayContent(app_id) {
    var b = document.getElementById(`${app_id}`)
    console.log(b)
    var coll = document.getElementById(`reimContentDiv${app_id}`)
    console.log(coll)
    if (coll) {
        b.classList.toggle("active");
        console.log(coll)
        if (coll.style.display == "block") {
            coll.style.display = "none"
        } else {
            coll.style.display = "block"
        }
    }
}


