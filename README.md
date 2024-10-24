# Rate limiter
<details>
  <summary>Click on me</summary>

  ## Leaking bucket
  ![image](https://github.com/user-attachments/assets/885a2610-635c-44d0-ae45-9604f9a9413d)

  The leaking bucket rate limiter controls data flow by discarding excess requests that exceed the bucket's capacity, allowing processing at a constant, predetermined rate.

  ## Token bucket
  ![image](https://github.com/user-attachments/assets/bd219bdc-fa7b-4b8d-b57e-d1aa2f39c250)

  The token bucket rate limiter fills up with tokens at a steady rate and uses tokens to handle bursts of requests.

  ## Fixed window
  ![image](https://github.com/user-attachments/assets/0dd3d79b-c6d7-4a11-b591-a0dd0b1774c4)

  The fixed window rate limiter tracks requests in a set time period, blocking any that exceed the limit until the next window starts.

  ## Slider window counter
  ![image](https://github.com/user-attachments/assets/43899961-60bc-459f-9e70-6ea50a37deb0)

  The sliding window counter rate limiter tracks requests over a rolling time period, allowing for a smoother limit on incoming requests.

  ## Slider window log
  ![image](https://github.com/user-attachments/assets/1c30b7aa-ba61-401f-8066-771f6a5ab489)

  The sliding window log rate limiter tracks request rates over time in logarithmic intervals, allowing fine-grained control over traffic bursts and smooth rate limiting.

</details>
