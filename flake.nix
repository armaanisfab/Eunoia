{
  description = "Example development environment flake";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    {
      self,
      nixpkgs,
      flake-utils,
    }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        packages = with pkgs; [
          python312
          kotlin-language-server
          supabase-cli
        ];
      in
      {
        devShell = pkgs.mkShell {
          buildInputs = packages;
          shellHook = ''
            echo "Welcome to the development shell!"
          '';
        };
      }
    );
}
