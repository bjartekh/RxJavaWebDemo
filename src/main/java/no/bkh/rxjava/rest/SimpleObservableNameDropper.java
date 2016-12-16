package no.bkh.rxjava.rest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;
import rx.functions.Action1;

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

	/**
	 * Assumes names are sent with / as delimiter (example: abc/def, ghi/jlk)
	 * 
	 * @param names
	 * @return
	 */
	@RequestMapping("/flatmap/{names}")
	public String flatMapProcessing(@PathVariable String[] names) {
		return flatMap(names);
		
	}

	public String flatMap(String... names) {

		StringBuilder sb = new StringBuilder();

		Observable.from(names).flatMap(s -> Observable.from(s.split("/")))
				.subscribe(i -> sb.append(i));

		logger.debug(sb.toString());
		logger.debug("Names sent:" + names);

		return sb.toString();
	}

}
