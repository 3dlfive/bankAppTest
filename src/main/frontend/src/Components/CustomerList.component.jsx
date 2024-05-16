import React, { useState, useEffect } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Container,
    Typography
} from '@mui/material';
import CustomerDetails from "./CustomerDetails.component";
import api from "../axiosConfig";
function CustomerList() {
    const [customers, setCustomers] = useState([]);

    useEffect(() => {  const fetchData = async () => {
        try {
            const response = await api.get('/api/customers');
            console.log(response)


            const data = await response.data;
            setCustomers(data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

        fetchData();
    }, []);
    return (
        <Container  >
            <Typography variant="h2" color="blue">Customer List</Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Age</TableCell>
                            <TableCell>Accounts</TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {customers.map(customer => (
                            <CustomerDetails key={customer.id} customer={customer} />
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
}

export default CustomerList;
