package sud.exmp1;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;

	public class BestBundle {

		public List<Bundle> bestBundle(List<Bundle> bundles,
				List<String> itemsToBuy, List<Bundle> bundlesBought) {
			if (itemsToBuy.isEmpty()) {
				return bundlesBought;
			}

			String firstItem = itemsToBuy.get(0);
			List<Bundle> bundlesWithItem0 = getBundleWithItem(bundles, firstItem);

			double minPrice = Double.MAX_VALUE;

			List<Bundle> bestBundles = null;

			for (Bundle b : bundlesWithItem0) {

				List<String> itemsToBuyCopy = new ArrayList<String>(itemsToBuy);
				for (String s : b.items) {
					itemsToBuyCopy.remove(s);
				}
				List<Bundle> bundlesBoughtCopy = new ArrayList<Bundle>(
						bundlesBought);
				bundlesBoughtCopy.add(b);

				List<Bundle> bundlesBoughtR = bestBundle(bundles, itemsToBuyCopy,
						bundlesBoughtCopy);

				double price = getPrice(bundlesBoughtR);
				if (price < minPrice) {
					minPrice = price;
					bestBundles = bundlesBoughtR;
				}

			}

			return bestBundles;

		}

		private double getPrice(List<Bundle> bundlesBoughtCopy) {
			double price = 0;
			for (Bundle bundle : bundlesBoughtCopy) {
				price += bundle.price;
			}
			return price;
		}

		private List<Bundle> getBundleWithItem(List<Bundle> bundles,
				String firstItem) {
			List<Bundle> bundlesWithItems = new ArrayList<BestBundle.Bundle>();
			for (Bundle bundle : bundles) {
				if (bundle.items.contains(firstItem)) {
					bundlesWithItems.add(bundle);
				}
			}
			return bundlesWithItems;
		}

		private static final class Bundle {
			private List<String> items;
			private int sNo;
			private double price;

			@Override
			public String toString() {
				return "Bundle [items=" + items + ", sNo=" + sNo + ", price="
						+ price + "]";
			}

		}

		public static void main(String[] args) {
			BestBundle bestBundle = new BestBundle();

			// 1. 5 | Burger
			// 2. 4 | French_Frice
			// 3. 8 | Coldrink
			// 4. 12 | Burger, French_Frice, Coldrink
			// 5. 14 | Burger, Coldrink

			Bundle bundle1 = getBundle(1, 5, Arrays.asList("B"));
			Bundle bundle2 = getBundle(2, 4, Arrays.asList("FF"));
			Bundle bundle3 = getBundle(3, 8, Arrays.asList("C"));
			Bundle bundle4 = getBundle(4, 12, Arrays.asList("B", "FF", "C"));
			Bundle bundle5 = getBundle(5, 14, Arrays.asList("B", "C"));

			List<Bundle> bundles = Arrays.asList(bundle1, bundle2, bundle3,
					bundle4, bundle5);
			List<String> itemsToBuy = Arrays.asList("C");

			System.out.println(bestBundle.bestBundle(bundles, itemsToBuy,
					new ArrayList<BestBundle.Bundle>()));
		}

		private static Bundle getBundle(int sNo, int price, List<String> asList) {
			Bundle bundle = new Bundle();
			bundle.sNo = sNo;
			bundle.price = price;
			bundle.items = asList;
			return bundle;
		}

	}
