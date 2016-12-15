package no.bkh.rxjava.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

@RestController
public class SimpleObservableNameDropper {

	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/sayhello/{names}")
	public String sayHello(@PathVariable String[] names) {
		return hello(names);
	}

	public String hello(String... names) {

		StringBuilder sb = new StringBuilder();
		Observable.from(names).subscribe(new Action1<String>() {

			@Override
			public void call(String s) {
				sb.append("Hello " + s + " \n");
			}

		});

		return sb.toString();
	}

	@RequestMapping("/flatmap/{names}")
	public String flatMapProcessing(@PathVariable String[] names) {
		return flatMap(names);
	}

	public String flatMap(String... names) {

		StringBuilder sb = new StringBuilder();

		Observable.just("abc/def", "ghi/jlk", "mno/pqe")
				.flatMap(s -> Observable.from(s.split("/")))
				.subscribe(i -> sb.append(i));

		System.out.println("test");
		return sb.toString();
	}

}
