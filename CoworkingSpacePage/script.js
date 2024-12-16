document.getElementById('userForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const userId = document.getElementById('userId').value;
    
    if (!userId) {
        alert('Por favor, ingresa un ID de usuario válido.');
        return;
    }

    document.getElementById('reportSection').classList.add('hidden');
    document.getElementById('errorMessage').classList.add('hidden');
    document.getElementById('reportList').innerHTML = '';
    fetch(`http://localhost:8282/v1/users/reports-user/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudieron obtener los reportes.');
            }
            return response.json();
        })
        .then(data => {
            const reports = data.reportDTOList;
            const user = data;
            const rol = data.role;
            const reportList = document.getElementById('reportList');
            if (reports.length === 0) {
                document.getElementById('errorMessage2').classList.remove('hidden');
            } else {
                document.getElementById('errorMessage2').classList.add('hidden');
                reports.forEach(report => {
                    const li = document.createElement('li');
                    li.innerHTML = `<strong>Tipo de Reporte:</strong> ${report.reportType}
                    <br><strong>Fecha de Creación:</strong> ${new Date(report.createdAt).toLocaleDateString()}
                    <br><strong> Usuario:</strong> ${user.name} ${user.lastName}
                    <br><strong> Email:</strong> ${user.email}
                    <br><strong> Rol:</strong> ${rol.name}
                    `;
                    reportList.appendChild(li);
                });
                document.getElementById('reportSection').classList.remove('hidden');
            }
        })
        .catch(error => {
            console.error(error);
            document.getElementById('errorMessage').classList.remove('hidden');
        });
});
