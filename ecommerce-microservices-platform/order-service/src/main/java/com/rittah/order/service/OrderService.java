package com.rittah.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rittah.order.dto.OrderRequest;
import com.rittah.order.dto.OrderResponse;

import jakarta.validation.Valid;

@Service
public class OrderService {

    public Page<OrderResponse> getAll(Pageable pageable) {
        return Page.empty();
    }

    public OrderResponse get(Long id) {
        return null;
    }

	public Object create(@Valid OrderRequest r, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<OrderResponse> customer(Long customerId, Pageable p) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderResponse updateStatus(Long id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}