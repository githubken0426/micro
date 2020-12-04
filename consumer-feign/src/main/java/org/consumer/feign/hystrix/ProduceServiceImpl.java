package org.consumer.feign.hystrix;

//@Component
public class ProduceServiceImpl implements ProduceService {

	@Override
	public String consumer() {
		return "[ProduceServiceImpl.consumer()] is not arrivable!";
	}

	@Override
	public String hystrix(int id) {
		return "[ProduceServiceImpl.hystrix(" + id + ")] is not arrivable!";
	}

}
