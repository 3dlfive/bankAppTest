import React from 'react';
import { TableRow, TableCell } from '@mui/material';
import { Link } from 'react-router-dom';

function CustomerDetails({ customer }) {
    return (
        <TableRow>
            <TableCell>{customer.id}</TableCell>
            <TableCell><Link to={"/customer/" + customer.id}>{customer.name}</Link></TableCell>
            <TableCell>{customer.email}</TableCell>
            <TableCell>{customer.age}</TableCell>
            <TableCell>
                <ul>
                {customer.accounts.map(account => (
                    <li key={account.number}><span>{account.number}</span> Ballance: <span>{account.balance}</span></li>

                ))}


                </ul>
            </TableCell>
        </TableRow>
    );
}

export default CustomerDetails;
