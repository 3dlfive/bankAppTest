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
    Typography,
    Button
} from '@mui/material';
import CustomerDetails from './CustomerDetails.component';
import CreateCustomerModal from './CreateCustomerModal.component';
import api from '../axiosConfig';

function CustomerList() {
    const [customers, setCustomers] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await api.get('/api/customers');
                const data = await response.data;
                setCustomers(data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const handleOpenModal = () => {
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const handleCustomerCreated = (newCustomer) => {
        setCustomers((prevCustomers) => [...prevCustomers, newCustomer]);
    };

    return (
        <Container>
            <Typography variant="h2" color="blue">Customer List</Typography>
            <Button variant="contained" color="primary" onClick={handleOpenModal} sx={{ mt: 2 }}>
                Create New Customer
            </Button>
            <TableContainer component={Paper} sx={{ mt: 2 }}>
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
                        {customers.map((customer) => (
                            <CustomerDetails key={customer.id} customer={customer} />
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <CreateCustomerModal
                open={isModalOpen}
                onClose={handleCloseModal}
                onCustomerCreated={handleCustomerCreated}
            />
        </Container>
    );
}

export default CustomerList;
