# TestTask1
To successfully complete this test, you need to create tests for test cases from the list below:
## UI Test Cases:

• All mandatory empty fields should have error description displayed after ‘Continue to checkout’ is pressed;

• ‘Cart’ successfully loaded when user opens the page

• Promo code correctly changes ‘Total’ field

• ‘Your order was placed’ displayed after form submission
## API Test Cases:

• API correctly calculates discount percent

• API returns error when credit card number length not equals 16

Any other additional tests would be a plus, but not required.
## Resources:

/items (GET) – returns JSON array: [{"name": string, "description": string, "price": int}]

/coupon?coupon=string (POST) – returns JSON object: {"discount":int} (Discount amount is the length of coupon string)

/checkout (POST):

Accepts JSON object {"firstName": string, "lastName": string, "paymentMethod": string, "cc-name": string, "cc-number": string, "cc-expiration": string, "cc-cvv": string}.

Returns JSON object: {"success":bool, "event": request-object, "message":error-string}
