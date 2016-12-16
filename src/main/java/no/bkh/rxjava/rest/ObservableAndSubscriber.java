package no.bkh.rxjava.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

@RestController
public class ObservableAndSubscriber {

	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/observe/{numbers}")
	public String sayHello(@PathVariable List<Integer> numbers) {
		return count(numbers);
	}

	private String count(List<Integer> numbers) {
		Summarizer summarizer = new Summarizer();

		Observable<Integer> source = Observable.from(numbers);

		source.subscribe(i -> summarizer.add(i));

		return String.valueOf(summarizer.getValue());
	}

	private class Summarizer {

		int val = 0;

		public void add(int i) {
			this.val += i;
		}

		public int getValue() {
			return this.val;
		}

	}
}
