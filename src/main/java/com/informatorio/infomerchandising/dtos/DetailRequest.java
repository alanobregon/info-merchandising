package com.informatorio.infomerchandising.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DetailRequest {

	@NotNull
	@Positive
	private Long product;

	@Positive
	private Long quantity = 1L;

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
